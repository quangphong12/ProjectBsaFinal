package com.example.projectbsa.Service;

import com.example.projectbsa.Entity.Voucher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VoucherService {
    void save(Voucher voucher);

    List<Voucher> getAllVoucher();

    Optional<Voucher> findById(long id);

    void deleteVoucher(long id);
    ///

    int countVC10K(long id);

    int countVC20K(long id);

    int countVCG5P(long id);

    void userVoucherVC10K(long id);

    void userVoucherVC20K(long id);

    void userVoucherVCG5P(long id);

    void addVoucherVC10K(int count,long id);

    void addVoucherVC20K(int count,long id);

    void addVoucherVCG5P(int count,long id);

    Optional<Voucher> findByIdUser(long id);

    long findIdUser(long id);

    Voucher findVoucherByIdUser(long id);
}
