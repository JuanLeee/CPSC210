package ca.ubc.cs.cpsc210.servicecard.tests;

import ca.ubc.cs.cpsc210.servicecard.model.FoodServicesCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.ubc.cs.cpsc210.servicecard.model.FoodServicesCard.CASH_BACK_REWARD;
import static ca.ubc.cs.cpsc210.servicecard.model.FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for FoodServiceCard class
public class FoodServicesCardTest {
    public static final int INITIAL_BALANCE = 10000;
    private FoodServicesCard testCard;

    @BeforeEach
    public void setUp() throws Exception {
        testCard = new FoodServicesCard(INITIAL_BALANCE);
    }

    @Test
    public void testConstructor() {
        assertEquals(INITIAL_BALANCE,testCard.getBalance());
        assertEquals(0,testCard.getRewardPoints());
    }

    @Test
    public void testReload(){
        testCard.reload(INITIAL_BALANCE);
        assertEquals(INITIAL_BALANCE*2,testCard.getBalance());
    }

    @Test
    public void testMakeOnePurchaseNoReward(){
        assertEquals(true,testCard.makePurchase(POINTS_NEEDED_FOR_CASH_BACK-1));
        assertEquals(INITIAL_BALANCE-POINTS_NEEDED_FOR_CASH_BACK+1,testCard.getBalance());
        assertEquals(POINTS_NEEDED_FOR_CASH_BACK-1,testCard.getRewardPoints());
    }

    @Test
    public void testMakeOnePurchaseYesRewardsZeroPoints(){
        assertEquals(true, testCard.makePurchase(POINTS_NEEDED_FOR_CASH_BACK));
        assertEquals(INITIAL_BALANCE-POINTS_NEEDED_FOR_CASH_BACK+CASH_BACK_REWARD,testCard.getBalance());
        assertEquals(0,testCard.getRewardPoints());
    }

    @Test
    public void testMakeOnePurchaseYesRewardsExtraPoints(){
        assertEquals(true, testCard.makePurchase(POINTS_NEEDED_FOR_CASH_BACK+POINTS_NEEDED_FOR_CASH_BACK/2));
        assertEquals(INITIAL_BALANCE-POINTS_NEEDED_FOR_CASH_BACK+CASH_BACK_REWARD-POINTS_NEEDED_FOR_CASH_BACK/2,testCard.getBalance());
        assertEquals(POINTS_NEEDED_FOR_CASH_BACK/2,testCard.getRewardPoints());
    }

    @Test
    public void testMakeOnePurchaseTwoRewardsExtraPoints(){
        assertEquals(true, testCard.makePurchase((POINTS_NEEDED_FOR_CASH_BACK*2)+(POINTS_NEEDED_FOR_CASH_BACK/2)));
        assertEquals(INITIAL_BALANCE-(POINTS_NEEDED_FOR_CASH_BACK*2)+(CASH_BACK_REWARD*2)-(POINTS_NEEDED_FOR_CASH_BACK/2),testCard.getBalance());
        assertEquals(POINTS_NEEDED_FOR_CASH_BACK/2,testCard.getRewardPoints());
    }

    @Test
    public void testMakeOnePurchaseFalse(){
        assertEquals(false, testCard.makePurchase(INITIAL_BALANCE+1000));
        assertEquals(INITIAL_BALANCE,testCard.getBalance());
        assertEquals(0,testCard.getRewardPoints());
    }

    @Test
    public void testMakeMultiplePurchasesMultipleRewardsExtraPoints(){
        assertEquals(true, testCard.makePurchase(POINTS_NEEDED_FOR_CASH_BACK*2+POINTS_NEEDED_FOR_CASH_BACK/4));
        assertEquals(INITIAL_BALANCE-POINTS_NEEDED_FOR_CASH_BACK*2+CASH_BACK_REWARD*2-POINTS_NEEDED_FOR_CASH_BACK/4,testCard.getBalance());
        assertEquals(POINTS_NEEDED_FOR_CASH_BACK/4,testCard.getRewardPoints());
        assertEquals(true, testCard.makePurchase(POINTS_NEEDED_FOR_CASH_BACK*2+POINTS_NEEDED_FOR_CASH_BACK/4));
        assertEquals(INITIAL_BALANCE-POINTS_NEEDED_FOR_CASH_BACK*2+CASH_BACK_REWARD*2-POINTS_NEEDED_FOR_CASH_BACK/4-POINTS_NEEDED_FOR_CASH_BACK*2+CASH_BACK_REWARD*2-POINTS_NEEDED_FOR_CASH_BACK/4,testCard.getBalance());
        assertEquals(POINTS_NEEDED_FOR_CASH_BACK/2,testCard.getRewardPoints());
    }
}