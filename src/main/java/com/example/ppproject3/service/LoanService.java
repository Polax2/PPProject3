package com.example.ppproject3.service;

import com.example.ppproject3.commonTypes.UserRole;
import com.example.ppproject3.controller.dto.*;
import com.example.ppproject3.errors.BookNotFoundError;
import com.example.ppproject3.errors.ForbiddenError;
import com.example.ppproject3.errors.LoanNotFoundError;
import com.example.ppproject3.errors.UserNotFoundError;
import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.infrastructure.entities.LoanEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;
import com.example.ppproject3.infrastructure.repository.AuthRepository;
import com.example.ppproject3.infrastructure.repository.BookRepository;
import com.example.ppproject3.infrastructure.repository.LoanRepository;
import com.example.ppproject3.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class LoanService extends OwnershipService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;


    @Autowired
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository, AuthRepository authRepository){
        super(authRepository);
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #loanDto.userId)")
    public CreateLoanResponseDto create(CreateLoanDto loanDto) {

        UserEntity user = userRepository.findById(loanDto.getUserId()).orElseThrow(()-> UserNotFoundError.create(loanDto.getUserId()));
        BookEntity book = bookRepository.findById(loanDto.getBookId()).orElseThrow(()-> BookNotFoundError.create(loanDto.getBookId()));

        LoanEntity loan = new LoanEntity();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(new Date(System.currentTimeMillis()));
        loan.setDueDate(loanDto.getDueDate());
        loanRepository.save(loan);


        return new CreateLoanResponseDto(loan.getLoanId(), loan.getUser().getUserId(),loan.getBook().getId(), loan.getDueDate(),loan.getLoanDate());
    }
    @PostAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, returnObject.user.id)")
    public GetLoanDto getOneById(long id){
        LoanEntity loan = loanRepository.findById(id).orElseThrow(() -> LoanNotFoundError.create(id));
        return mapLoan(loan);
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #userId)")
    public GetLoansPageResponseDto getAll(Long userId, int page, int size){
        Page<LoanEntity> loansPage;

        Pageable pageable= PageRequest.of(page,size);

        if(userId == null){
            loansPage = loanRepository.findAll(pageable);
        }else{
            loansPage = loanRepository.findByUserUserId(userId,pageable);
        }

        List<GetLoanDto> loanDto = loansPage.getContent().stream().map(this::mapLoan).toList();
        return new GetLoansPageResponseDto(loanDto, loansPage.getNumber(), loansPage.getTotalElements(), loansPage.getTotalPages(), loansPage.hasNext());
    }

    public GetLoanDto  mapLoan(LoanEntity loan){
        GetUserDto user =  new GetUserDto(loan.getUser().getUserId(), loan.getUser().getName(), loan.getUser().getSurname(), loan.getUser().getEmail());
        GetBookDto book =  new GetBookDto(
                loan.getBook().getId(),
                loan.getBook().getIsbn(),
                loan.getBook().getTitle(),
                loan.getBook().getAuthor(),
                loan.getBook().getPublisher(),
                loan.getBook().getYearPublished(),
                loan.getBook().getAvailableCopies()>0);
        return  new GetLoanDto(loan.getLoanId(), book , user, loan.getLoanDate(), loan.getDueDate());
    }

    public void delete(long id){
        if(!loanRepository.existsById(id)){
            throw LoanNotFoundError.create(id);
        }
        loanRepository.deleteById(id);
    }

    public void returnBook(long id, Authentication authentication){
        LoanEntity loan = loanRepository.findById(id).orElseThrow(() -> LoanNotFoundError.create(id));
        var auth = getCurrentAuth(authentication);

        if (!isOwnerOrAdmin(auth, loan.getUser().getUserId())){
            throw ForbiddenError.create();
        }

        if(loan.getReturnDate() != null) {
            return;
        }

        loan.setReturnDate(new Date(System.currentTimeMillis()));
        loanRepository.save(loan);
    }
}
