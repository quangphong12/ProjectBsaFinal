package com.example.projectbsa.Repository;

import com.example.projectbsa.Entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    @Query(value="Select * from Voucher v where v.id_user=?1",nativeQuery = true)
    Optional<Voucher> findByIdUser(long id);

    @Query(value="SELECT v.VC10K FROM Voucher v where v.id_user=?1",nativeQuery = true)
    int countVC10K(long id);

    @Query(value="SELECT v.VC20K FROM Voucher v where v.id_user=?1",nativeQuery = true)
    int countVC20K(long id);

    @Query(value="SELECT v.VCG5P FROM Voucher v where v.id_user=?1",nativeQuery = true)
    int countVCG5P(long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="UPDATE Voucher SET VC10K = VC10K -1 WHERE id_user=?1",nativeQuery = true)
    void userVoucherVC10K(long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="UPDATE Voucher SET VC20K = VC20K -1 WHERE id_user=?1",nativeQuery = true)
    void userVoucherVC20K(long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="UPDATE Voucher SET VCG5P = VCG5P -1 WHERE id_user=?1",nativeQuery = true)
    void userVoucherVCG5P(long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="UPDATE Voucher SET VC10K = VC10K +?1 WHERE id_user=?2",nativeQuery = true)
    void addVoucherVC10K(int count,long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="UPDATE Voucher SET VC20K = VC20K +?1 WHERE id_user=?2",nativeQuery = true)
    void addVoucherVC20K(int count,long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="UPDATE Voucher SET VCG5P = VCG5P +?1 WHERE id_user=?2",nativeQuery = true)
    void addVoucherVCG5P(int count,long id);

    @Query(value="select v.id_user from Voucher v where v.id=?1", nativeQuery = true)
    long findIdUser(long id);

    @Query(value="select * from Voucher v where v.id_user=?1",nativeQuery = true)
    Voucher findVoucherByIdUser(long id);

}
