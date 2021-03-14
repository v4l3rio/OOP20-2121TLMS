//package model;
//
///**
// * LuciferBasicModel class.
// * 
// */
//public abstract class PlayerBasicModel {
//
//
//    private static final int MAX_HEARTS = 3;
//    private static final double START_D = 0;
//    private static final double PLAYER_WIDTH = 70;
//    private static final double PLAYER_HEIGHT = 150;
//
//    private PlayerDirection lastDirection = PlayerDirection.RIGHT;
//    private static final double MAX_X = 1280 - PLAYER_WIDTH; //RIVEDERE
//    private static final double MIN_X = 0;
//
//    //Constructor:
//    
//
//    public final int getLuciferMaxHealth() {
//        return MAX_HEARTS;
//    }
//
//    public final double getLuciferDamageOnContact() {
//        return LUCIFER_DAMAGE_ON_CONTACT;
//    }
//
//    public final double getStartD() {
//        return START_D;
//    }
//
//    public final double getLuciferWidth() {
//        return LUCIFER_WIDTH;
//    }
//
//    public final double getLuciferHeight() {
//        return LUCIFER_HEIGHT;
//    }
//
//    public final int getLastDirection() {
//        return lastDirection;
//    }
//
//    public final double getLuciferMaxX() {
//        return LUCIFER_MAX_X;
//    }
//
//    public final double getLuciferMinX() {
//        return LUCIFER_MIN_X;
//    }
//
//    public static final double getRangedAttackDamage() {
//        return RANGED_ATTACK_DAMAGE;
//    }
//
//    public static final double getRangedAttackSpeed() {
//        return RANGED_ATTACK_SPEED;
//    }
//
//    public static final double getRangedAttackChargeTime() {
//        return RANGED_ATTACK_CHARGE_TIME;
//    }
//
//    public static final double getFinalAttackChargeTime() {
//        return FINAL_ATTACK_CHARGE_TIME;
//    }
//
//    public final double getRangedAttackChargeCounter() {
//        return rangedAttackChargeCounter;
//    }
//
//    public final double getFinalAttackChargeCounter() {
//        return finalAttackChargeCounter;
//    }
//
//    public static double getAnimationTime() {
//        return ANIMATION_TIME;
//    }
//
//    public final double getAnimationCounter() {
//        return animationCounter;
//    }
//
//    public static double getRangedAttacks() {
//        return RANGED_ATTACKS;
//    }
//
//    public static double getRangedAttacksSpread() {
//        return RANGED_ATTACKS_SPREAD;
//    }
//
//    public final void setLastDirection(final int lastDirection) {
//        this.lastDirection = lastDirection;
//    }
//
//    public final void setRangedAttackChargeCounter(final double rangedAttackChargeCounter) {
//        this.rangedAttackChargeCounter = rangedAttackChargeCounter;
//    }
//
//    public final void setFinalAttackChargeCounter(final double finalAttackChargeCounter) {
//        this.finalAttackChargeCounter = finalAttackChargeCounter;
//    }
//
//    public final void setAnimationCounter(final double animationCounter) {
//        this.animationCounter = animationCounter;
//    }

//    public final double getMeleeAttackChargeCounter() {
//        return meleeAttackChargeCounter;
//    }
//
//    public final void setMeleeAttackChargeCounter(final double meleeAttackChargeCounter) {
//        this.meleeAttackChargeCounter = meleeAttackChargeCounter;
//    }
//
//    public static double getMeleeAttackChargeTime() {
//        return MELEE_ATTACK_CHARGE_TIME;
//    }
//
//    //Methods:
//    /**
//     * check bounds to avoid Lucifer to exit from screen.
//     * 
//     * @param isLeftPressed
//     * @param isRightPressed
//     * @return boolean
//     * 
//     */
//    public final boolean checkBounds(final boolean isLeftPressed, final boolean isRightPressed) {
//        double newX = this.getX();
//        // horizontal
//        if (Double.compare(newX, this.getLuciferMinX()) <= 0 && isLeftPressed) {
//          this.setX(this.getLuciferMinX());
//          this.setDx(0);
//          return true;
//        } else if (Double.compare(newX, this.getLuciferMaxX()) >= 0 && isRightPressed) {
//          this.setX(this.getLuciferMaxX());
//          this.setDx(0);
//          return true;
//        } else {
//            return false;
//        }
//    }
//}