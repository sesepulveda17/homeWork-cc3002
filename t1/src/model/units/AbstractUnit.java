package model.units;

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
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit{

  protected final List<IEquipableItem> items = new ArrayList<>();
  private int currentHitPoints;
  private final int movement;
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
   */
  protected AbstractUnit(final int hitPoints, final int movement,
      final Location location, final int maxItems, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }

    /**
     *
     * @param item
     */
  public void equipItem(IEquipableItem item){
      item.equipTo(this);
  }

    /**
     *
     * @return
     */
  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

    /**
     *
     * @param remove
     */
  @Override
  public void setRemoveHitPoints(int remove) { this.currentHitPoints = this.currentHitPoints - remove; }

    /**
     *
     * @return
     */
  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  //@Override
  //public IEquipableItem getEquippedItem() {
  //  return equippedItem;
  //}

  //@Override
  //public void setEquippedItem(final IEquipableItem item) {
  //  this.equippedItem = item;
  //}

    /**
     *
     * @return
     */
  @Override
  public Location getLocation() {
    return location;
  }

    /**
     *
     * @param location
     */
  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

    /**
     *
     * @return
     */
  @Override
  public int getMovement() {
    return movement;
  }

    /**
     *
     * @param targetLocation
     */
  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }
}

