package com.example.projectbsa.Service.Impl;

import com.example.projectbsa.Entity.Voucher;
import com.example.projectbsa.Repository.VoucherRepository;
import com.example.projectbsa.Service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;

    @Override
    public void save(Voucher voucher) {
        voucherRepository.save(voucher);
    }

    @Override
    public List<Voucher> getAllVoucher() {
        return (List<Voucher>) voucherRepository.findAll();
    }

    @Override
    public Optional<Voucher> findById(long id) {
        return voucherRepository.findById(id);
    }

    @Override
    public void deleteVoucher(long id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public int countVC10K(long id) {
        return voucherRepository.countVC10K(id);
    }

    @Override
    public int countVC20K(long id) {
        return voucherRepository.countVC20K(id);
    }

    @Override
    public int countVCG5P(long id) {
        return voucherRepository.countVCG5P(id);
    }

    @Override
    public void userVoucherVC10K(long id) {
        voucherRepository.userVoucherVC10K(id);
    }

    @Override
    public void userVoucherVC20K(long id) {
        voucherRepository.userVoucherVC20K(id);
    }

    @Override
    public void userVoucherVCG5P(long id) {
        voucherRepository.userVoucherVCG5P(id);
    }

    @Override
    public void addVoucherVC10K(int count, long id) {
        voucherRepository.addVoucherVC10K(count,id);
    }

    @Override
    public void addVoucherVC20K(int count, long id) {
        voucherRepository.addVoucherVC20K(count,id);
    }

    @Override
    public void addVoucherVCG5P(int count, long id) {
        voucherRepository.addVoucherVCG5P(count,id);
    }

    @Override
    public Optional<Voucher> findByIdUser(long id) {
        return voucherRepository.findByIdUser(id);
    }

    @Override
    public long findIdUser(long id) {
        return voucherRepository.findIdUser(id);
    }

    @Override
    public Voucher findVoucherByIdUser(long id) {
        return voucherRepository.findVoucherByIdUser(id);
    }
}
