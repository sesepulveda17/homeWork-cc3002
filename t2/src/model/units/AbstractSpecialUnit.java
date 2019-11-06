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

package model.units;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.handlers.ResponseSpecialUnit;

/**
 * Abstract class that defines common method for the special units
 * this units can add a ResponseSpecialUnit like listener
 *
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since v2.0
 */
public abstract class AbstractSpecialUnit extends AbstractUnitCombative implements SpecialUnit {

    /**
     * Creates a new Unit.
     *
     * @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     * @param location  the current position of this unit on the map
     * @param maxItems  maximum amount of items this unit can carry
     * @param items
     */
    protected AbstractSpecialUnit(int hitPoints, int movement, Location location, int maxItems, IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, items);
    }

    @Override
    public void addResponseSpecialUnit(ResponseSpecialUnit plc){
        changeSupport.addPropertyChangeListener(plc);
    }
}
