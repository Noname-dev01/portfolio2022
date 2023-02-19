package portfolio2022.portfolio2022.dailyshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio2022.portfolio2022.dailyshop.domain.entity.ChargeList;
import portfolio2022.portfolio2022.dailyshop.domain.entity.ChargeStatus;
import portfolio2022.portfolio2022.dailyshop.repository.ChargeListRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChargeListService {

    private final ChargeListRepository chargeListRepository;

    /**
     * 충전 내역 전체 조회
     */
    public List<ChargeList> findChargeListAll(){
        return chargeListRepository.findAll();
    }

    /**
     * 충전 승인 하기
     */
    @Transactional
    public void chargeOkay(Long chargeListId){
        ChargeList chargeList = chargeListRepository.findById(chargeListId).get();
        int chargeAmount = chargeList.getChargeAmount();
        chargeList.setChargeStatus(ChargeStatus.COMPLETE);
        chargeList.getMember().setCoin(chargeList.getMember().getCoin()+chargeAmount);
    }

    /**
     * 충전 내역 삭제
     */
    @Transactional
    public void deleteChargeList(Long id){
        chargeListRepository.deleteById(id);
    }
}
