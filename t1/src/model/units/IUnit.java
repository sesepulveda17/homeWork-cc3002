package model.units;

import java.util.List;

import model.items.*;
import model.items.Axe;
import model.items.Bow;
import model.items.Spear;
import model.items.Staff;
import model.items.Sword;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public interface IUnit {

  /**
   *
   * @param item
   * to equip a unit with a item
   */
  void equipItem(IEquipableItem item);

  /**
   * @param item
   * Anything item that isn't in the list
   */
  void equipItemOther(IEquipableItem item);

  /**
   * @param item the item to equip
   */
  void equipItemBow(Bow item);

  /**
   *
   * @param item
   * to equip Axe
   */
  void equipItemAxe(Axe item);
  /**
   *
   * @param item
   * to equip Sword
   */
  void equipItemSword(Sword item);
  /**
   *
   * @param item
   * to equip Staff
   */
  void equipItemStaff(Staff item);
  /**
   *
   * @param item
   * to equip Spear
   */
  void equipItemSpear(Spear item);
  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   * add a item to the unit
   * @param item
   */
  void addItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

// HIT POINTS
  /**
   * @return hit points of the unit
   */
  int getCurrentHitPoints();

  /**
   * @param change
   * the hit points to be remove
   */
  void setCurrentHitPoints(int change);

  /**
   * A unit can receive a Attack normal
   * @param attack
   */
  void receiveAttack(IEquipableItem attack);

  /**
   * A unit receive a Attack that seriously affect it
   * @param attack
   */
  void receiveAttackWeakness(IEquipableItem attack);

  /**
   * A unit receive a Attack that not affect it too much.
   * @param attack
   */
  void receiveAttackResistant(IEquipableItem attack);

  /**
   * A unit receive a Attack that recovers it
   * @param attack
   */
  void receiveRecovery(IEquipableItem attack);

  /**
   * Determine if the unit enemy is in the range of army's unit that attack
   * @param unit
   * @return
   */
  boolean isInRange(IUnit unit);

  /**
   * A unit can exchange his item i with other unit if and only if both have items.
   * The unit receive a Item j of the other unit.
   * @param unit
   */
  void exchangeItem(IUnit unit,int i, int j);
}

