package model.units;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.items.*;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit{

  protected final List<IEquipableItem> items = new ArrayList<>();
  private int currentHitPoints;
  private final int movement;
  private final int maxItems;
  protected IEquipableItem equippedItem;
  private Location location;

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   * @param items
   *     items that the unit not use but can carry
   */
  protected AbstractUnit(final int hitPoints, final int movement,
      final Location location, final int maxItems, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.maxItems = maxItems;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }


  @Override
  public void equipItem(IEquipableItem item){
    if(items.contains(item)){
      item.equipTo(this);
    }
  }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public void setCurrentHitPoints(int hp) {
    this.currentHitPoints = hp;
  }

  /**
   * Add a element to list of items's unit
   * @param item
   */
  @Override
  public void addItem(IEquipableItem item) {
    int n = items.size();
    if(n<this.maxItems){
      this.items.add(item);
    }
  }

  @Override
  public IEquipableItem removeItem(IEquipableItem item) {
    if(!items.isEmpty()){
      if(this.items.contains(item)) {
        for(int i=0; i<this.getItems().size(); i++){
          if(item.equals(this.getItems().get(i))){
            return this.items.remove(i);
          }
        }
      }
    }
    return null;
  }

  @Override
  public boolean isItemFull() {
    return this.maxItems==items.size();
  }

  /**
   * Reduce hp in unit that receive a attack
   * @param attack
   */
  public void receiveAttack(IEquipableItem attack){
    this.currentHitPoints -= attack.getPower();
  }

  /**
   *
   * @param attack
   */
  public void receiveAttackWeakness(IEquipableItem attack){
    this.currentHitPoints -= (int) (attack.getPower() *1.5);
  }

  /**
   *
   * @param attack
   */
  public void receiveAttackResistant(IEquipableItem attack){
    this.currentHitPoints -= attack.getPower() - 20;
  }

  /**
   * Increase hp in unit that receive a attack
   * @param recovery
   */
  public void receiveRecovery(IEquipableItem recovery){
    this.currentHitPoints += recovery.getPower();
  }

  @Override
  public boolean isInRange(IUnit unit) {
    int maxRange = this.getEquippedItem().getMaxRange();
    int minRange = this.getEquippedItem().getMinRange();
    double distance = this.getLocation().distanceTo(unit.getLocation());
    if(minRange <= distance  &&  maxRange >= distance){
      return true;
    }
    return false;
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() { return equippedItem; }

  /**
   * Change the equippedItem for a item new
   * @param item
   */
  public void setEquippedItem(IEquipableItem item) { this.equippedItem = item;  }

  @Override
  public void giveItem(IUnit unit, IEquipableItem item) {
    if(!this.getItems().isEmpty()){
      if(!unit.isItemFull()){
        if(this.getLocation().distanceTo(unit.getLocation())==1){
          IEquipableItem itemA = this.removeItem(item);
          if(itemA.equals(this.getEquippedItem())){
            this.setEquippedItem(null);
          }
          unit.addItem(itemA);
        }
      }
    }
  }

  @Override
  public void receiveItem(IUnit unit, IEquipableItem item){
    if(!unit.getItems().isEmpty()){
      if(!this.isItemFull()){
        if(this.getLocation().distanceTo(unit.getLocation())==1){
          IEquipableItem itemB = unit.removeItem(item);
          if(itemB.equals(unit.getEquippedItem())){
            unit.setEquippedItem(null);
          }
          this.addItem(itemB);
        }
      }
    }
  }

  @Override
  public Location getLocation() {
    return location;
  }
  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof IUnit && ((IUnit) obj).getCurrentHitPoints() == currentHitPoints
            && ((IUnit) obj).getLocation().equals(location) && ((IUnit) obj).getEquippedItem().equals(equippedItem)
            && ((IUnit) obj).getMovement()==movement && ((IUnit) obj).getItems().equals(items);
  }
}

