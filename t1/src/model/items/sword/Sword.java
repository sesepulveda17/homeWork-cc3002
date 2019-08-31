package model.items.sword;

import model.items.AbstractItem;
import model.items.axe.AttackAxe;
import model.items.bow.AttackBow;
import model.items.spears.AttackSpears;
import model.items.staff.AttackStaff;
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
  }

  @Override
  public void receiveBowAttack(AttackBow attackBow) {
    receiveAttack(attackBow);
  }

  @Override
  public void receiveAxeAttack(AttackAxe attackAxe) {
    receiveResistantAttack(attackAxe);
  }

  @Override
  public void receiveSwordsAttack(AttackSword attackSword) {
    receiveAttack(attackSword);
  }

  @Override
  public void receiveSpearsAttack(AttackSpears attackSpears) {
    receiveWeaknessAttack(attackSpears);
  }
  @Override
  public void receiveStaffAttack(AttackStaff attackStaff) {
    receiveRecovery(attackStaff);
  }
}
