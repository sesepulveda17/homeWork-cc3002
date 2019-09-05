package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private final String name;
  private final int power;
  protected int maxRange;
  protected int minRange;
  private IUnit owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  public AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  /**
   * Each unit will have a item different
   * the unit only can to change his item for other equals.
   * @param unit
   */
  @Override
  public void equipTo(final IUnit unit) {
    unit.equipItemOther(this);
    owner = unit;
  }

  @Override
  public IUnit getOwner() {
    return owner;
  }

  @Override
  public void setOwner(IUnit owner) {
    this.owner = owner;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public int getMinRange() {
    return minRange;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }

  //COMBAT

  @Override
  public void attack(IUnit other) {
    this.attack(other);
  }

  /**
   * A item can receive a Attack without damage additional
   * @param attack
   */
  protected void receiveAttack(IEquipableItem attack) {
    this.getOwner().receiveAttack(attack);
  }

  protected void receiveRecovery(IEquipableItem attack) { this.getOwner().receiveRecovery(attack); }

  @Override
  public void receiveAxeAttack(Axe attackAxe) { receiveAttack(attackAxe); }

  @Override
  public void receiveBowAttack(Bow attackBow) {
    receiveAttack(attackBow);
  }


  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    receiveAttack(attackSpears);
  }

  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    receiveAttack(attackSword);
  }


  /**
   * Receives an attack to which this Pokémon is weak.
   *
   * @param attack
   *     Received attack.
   */
  protected void receiveWeaknessAttack(IEquipableItem attack){
    int a = (int) (this.getOwner().getCurrentHitPoints() - attack.getPower() * 1.5);
    this.getOwner().setCurrentHitPoints(a);
  }


  /**
   * Receives an attack to which this Pokémon is resistant.
   *
   * @param attack
   *     Received attack.
   */
  protected void receiveResistantAttack(IEquipableItem attack) {
    int a = (int) (this.getOwner().getCurrentHitPoints() - attack.getPower() + 20);
    this.getOwner().setCurrentHitPoints(a);
  }
  //END COMBAT

  @Override
  public boolean equals(Object obj){
    return obj instanceof IEquipableItem && ((IEquipableItem) obj).getPower()==this.getPower()
            && ((IEquipableItem) obj).getMinRange() == this.getMinRange()
            && ((IEquipableItem) obj).getMaxRange() == this.getMaxRange()
            && ((IEquipableItem) obj).getName() == this.getName()
            && ((IEquipableItem) obj).getOwner() ==this.getOwner();
  }

}
