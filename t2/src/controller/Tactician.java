/*
 * The MIT License
 *
 * Copyright (c) 2019 Google, Inc. http://angularjs.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import model.items.IEquipableItem;
import model.units.IUnit;
import model.units.NormalUnit;
import model.units.SpecialUnit;
import model.units.handlers.ResponseNormalUnit;
import model.units.handlers.ResponseSpecialUnit;

/**
 * A Tactician is the interface for the interaction between the user and the model of the game.
 * In the developer of the code, a tactician represent to a player, so it must to know
 * all the features of the units and the quantity of units that have.
 *
 * Besides, a tactician have a reference to the unit currently selected
 *
 *
 *
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 *
 */


public class Tactician {

    private boolean status;
    private final String mark;
    private List<IUnit> units = new ArrayList<>();
    private IUnit currentUnit;
    private PropertyChangeSupport
            changesNormalUnit = new PropertyChangeSupport(this),
            changesSpecialUnit = new PropertyChangeSupport(this),
            changesStatusTactician = new PropertyChangeSupport(this);

    /**
     * Constructor to specify an alternative source of moves
     *
     * @param markName name for mark to player
     *
     */
    public Tactician(final String markName){
        this.mark = markName;
        this.status = true;
    }

    /**
     * @return Tactician's name
     */
    public String getName() {
        return mark;
    }

    /**
     * @return all the player's units
     */
    public List<IUnit> getUnits(){
        return units;
    }

    /**
     * Remove a normal unit die and set the unit in location to null
     * @param unitDeleted
     */
    public void removeUnit(NormalUnit unitDeleted){
        if(units.contains(unitDeleted)) {
            int initSize = getUnits().size();
            unitDeleted.getLocation().setUnit(null);
            units.remove(unitDeleted);
            changesNormalUnit.firePropertyChange(
                    new PropertyChangeEvent(
                            this,
                            "Normal Unit deleted",
                            initSize,
                            getUnits())
            );
        }
    }


    /**
     * Remove a special unit die and set the unit in location to null
     * @param specialUnit
     */
    public void removeSpecialUnit(SpecialUnit specialUnit) {
        if(units.contains(specialUnit)) {
            int initSize = getUnits().size();
            specialUnit.getLocation().setUnit(null);
            units.remove(specialUnit);
            changesSpecialUnit.firePropertyChange(
                    new PropertyChangeEvent(
                            this,
                            "Special Unit deleted",
                            initSize,
                            getUnits()
                    )
            );
        }
    }

    /**
     * Added a unit to the inventory of unit from player
     * @param unitAdded added with hp full
     */
    public void addUnitInventory(NormalUnit unitAdded) {
        final ResponseNormalUnit respNormalUnit = new ResponseNormalUnit(this);
        unitAdded.addObserver(respNormalUnit);
        units.add(unitAdded);
    }

    /**
     * Add a hero to the inventory of units from player
     * @param unitHero added
     */
    public void addUnitHero(SpecialUnit unitHero) {
        final ResponseSpecialUnit respSpecialUnit = new ResponseSpecialUnit(this);
        unitHero.addObserver(respSpecialUnit);
        units.add(unitHero);
    }

    /**
     * @return getter the item equipped in the current unit of tactician
     */
    public IEquipableItem getEquipItemCurrentUnit(){
        return currentUnit.getEquippedItem();
    }

    /**
     * @return getter the item's inventory of the tactician
     */
    public List<IEquipableItem> getItemsCurrentUnit(){
        return currentUnit.getItems();
    }

    /**
     * @return the current HP of the unit
     */
    public double hitPointsCurrentUnit(){ return getCurrentUnit().getCurrentHitPoints(); }

    /**
     * @return the maximum HP of the unit
     */
    public double maxHitPointsCurrentUnit(){ return getCurrentUnit().getMaxCurrentHitPoints(); }

    /**
     * @return get the reference to the current unit of tactician
     */
    public IUnit getCurrentUnit() { return currentUnit; }

    /**
     * @param unit that will change to current unit
     */
    public void setCurrentUnit(IUnit unit) {
         if(getUnits().contains(unit)) this.currentUnit = unit;
    }

    // STATUS PLAYER

    /**
     * Retire a tactician of the game
     */
    public void retire(){
        changesStatusTactician.firePropertyChange(
                new PropertyChangeEvent(
                        this,
                        "change-status",
                        this.getStatus(),
                        false
                )
        );
    }

    /**
     * @return the current unit's status
     */
    public boolean getStatus() { return this.status; }

    /**
     * Set the item equipped to other item
     *
     * @param item
     */
    public void setEquipItem(IEquipableItem item) {
       this.currentUnit.changeEquippedItem(item);
    }

    /**
     * The current unit generate a attack to a target unit
     *
     * @param enemy
     */
    public void generateAttack(IUnit enemy){
        getCurrentUnit().attack(enemy);
    }

    /**
     * The current unit give the item selected for the controller
     * to a target unit.
     *
     * @param target
     * @param itemSelected
     */
    public void giveItemToUnit(IUnit target, IEquipableItem itemSelected){
        if(this.getUnits().contains(target)){
            this.getCurrentUnit().giveItem(target,itemSelected);
        }
    }

    /**
     * Add a controller like listener in the change in the
     * quantity of tactician's normal units.
     *
     * @param plc controller of the game
     */
    public void addObserverNormalUnit(PropertyChangeListener plc){
        changesNormalUnit.addPropertyChangeListener(plc);
    }

    /**
     * Add a controller like listener in the change in the
     * quantity of tactician's special units.
     *
     * @param plc controller of the game
     */
    public void addObserverSpecialUnit(PropertyChangeListener plc){
        changesSpecialUnit.addPropertyChangeListener(plc);
    }

    public void addObserverStatus(PropertyChangeListener plc){
        changesStatusTactician.addPropertyChangeListener(plc);
    }

}
