package model.units.factoryUnit;

import model.items.Spear;
import model.items.factoryItem.FactoryItemProvider;
import model.items.factoryItem.IFactoryItem;
import model.items.factoryItem.ItemType;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryProviderUnitTest {
    private FactoryProviderUnit factory;

    @BeforeEach
    public void setUp() {
        factory = new FactoryProviderUnit();
    }

    @Test
    public void makeUnitAlpaca(){
        IFactoryUnit alpaca = factory.makeUnit(UnitType.ALPACA);
        assertEquals(alpaca.createUnit().getClass(), Alpaca.class);
    }

    @Test
    public void makeUnitArcher(){
        IFactoryUnit archer = factory.makeUnit(UnitType.ARCHER);
        assertEquals(archer.createUnit().getClass(),Archer.class);
    }

    @Test
    public void makeUnitCleric(){
        IFactoryUnit cleric = factory.makeUnit(UnitType.CLERIC);
        assertEquals(cleric.createUnit().getClass(),Cleric.class);
    }

    @Test
    public void makeUnitFighter(){
        IFactoryUnit fighter = factory.makeUnit(UnitType.FIGHTER);
        assertEquals(fighter.createUnit().getClass(),Fighter.class);
    }

    @Test
    public void makeUnitHero(){
        IFactoryUnit hero = factory.makeUnit(UnitType.HERO);
        assertEquals(hero.createUnit().getClass(),Hero.class);
    }
    @Test
    public void makeUnitSorcerer(){
        IFactoryUnit sorcerer = factory.makeUnit(UnitType.SORCERER);
        assertEquals(sorcerer.createUnit().getClass(),Sorcerer.class);
    }
    @Test
    public void makeUnitSwordMaster(){
        IFactoryUnit swordmaster = factory.makeUnit(UnitType.SWORDMASTER);
        assertEquals(swordmaster.createUnit().getClass(),SwordMaster.class);
    }

    @Test
    public void illegalItemArgument() throws IllegalArgumentException{
        boolean thrown = false;
        try {
            IFactoryUnit aUnit = factory.makeUnit(UnitType.OTHER);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
