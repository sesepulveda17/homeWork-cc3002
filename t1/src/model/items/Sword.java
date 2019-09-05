package model.items;

import model.units.IUnit;

/**
 * This class represents a <i>Sword</i> type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Sword extends AbstractItem {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipItemSword(this);
    this.setOwner(unit);
  }

  @Override
  public void receiveBowAttack(Bow attackBow) {
    receiveAttack(attackBow);
  }
  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    this.receiveAttack(attackSword);
    if(this.getOwner().getCurrentHitPoints()>0){
      if(attackSword.getOwner().getCurrentHitPoints()>0){
        attackSword.getOwner().receiveAttack(this);
      }
    }
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    this.receiveResistantAttack(attackAxe);
    if(this.getOwner().getCurrentHitPoints()>0) {
      if(attackAxe.getOwner().getCurrentHitPoints()>0){
        attackAxe.getOwner().receiveAttackWeakness(this);
      }
    }
  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    this.receiveWeaknessAttack(attackSpears);
    if(this.getOwner().getCurrentHitPoints()>0){
      if(attackSpears.getOwner().getCurrentHitPoints()>0){
        attackSpears.getOwner().receiveAttackResistant(this);
      }
    }
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Sword && super.equals(obj);
  }
}
