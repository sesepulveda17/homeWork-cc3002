package model.items.magic;

import model.items.*;
import model.units.IUnit;

/**
 * This class represents an <i>Darkness</i>.
 * <p>
 * Darkness is weakness counter Light
 * But resistant counter Soul
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public class Darkness extends AbstractItemMagic implements IAttack {
    /**
     * Create a new Darkness item
     *
     * @param name
     *      the name of the darkness
     * @param power
     *      the power of the darkness
     * @param minRange
     *      the minimum range of the item
     * @param maxRange
     *      the maximum range of the item
     */
    public Darkness(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void equipTo(IUnit unit) {
        unit.equipItemDarkness(this);
        this.setOwner(unit);
    }

    @Override
    public void giveMagicAttack(IEquipableItem enemyAttack){
        enemyAttack.receiveDarknessAttack(this);
    }

    @Override
    public void receiveDarknessAttack(Darkness attackDarkness) {
        super.receiveAttackNormal(attackDarkness);
    }

    @Override
    public void receiveLightAttack(Light attackLight) {
        super.receiveWeakAttack(attackLight);
    }

    @Override
    public void receiveSoulAttack(Soul attackSoul) {
        super.receiveSoftAttack(attackSoul);
    }
}
