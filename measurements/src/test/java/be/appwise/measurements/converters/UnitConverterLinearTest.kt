package be.appwise.measurements.converters

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class UnitConverterLinearTest {

    @Test
    fun testLinearity() {
        val coefficient = 7.0
        val baseUnitConverter = UnitConverterLinear(coefficient = coefficient)
        assertEquals(baseUnitConverter.value(coefficient), 1.0, 1e-9)
        assertEquals(baseUnitConverter.baseUnitValue(1.0), coefficient, 1e-9)
    }

    @Test
    fun testEquality() {
        val u1 = UnitConverterLinear(coefficient = 1.0, constant = 2.0)
        val u2 = UnitConverterLinear(coefficient = 1.0, constant = 2.0)
        assertEquals(u1, u2)
        assertEquals(u2, u1)
        assertEquals(u1.hashCode(), u2.hashCode())
        assertEquals(u2.hashCode(), u1.hashCode())

        val u3 = UnitConverterLinear(coefficient = 1.0, constant = 3.0)
        assertNotEquals(u1, u3)
        assertNotEquals(u3, u1)
        assertNotEquals(u1.hashCode(), u3.hashCode())
        assertNotEquals(u3.hashCode(), u1.hashCode())

        val u4 = UnitConverterLinear(coefficient = 2.0, constant = 2.0)
        assertNotEquals(u1, u4)
        assertNotEquals(u4, u1)
        assertNotEquals(u1.hashCode(), u4.hashCode())
        assertNotEquals(u4.hashCode(), u1.hashCode())
    }
}