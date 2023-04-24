package be.appwise.measurements

import be.appwise.measurements.units.Dimension
import be.appwise.measurements.units.Unit

class Measurement<in UnitType : Unit>(var value: Double, unit: UnitType) {

    companion object {

        fun <UnitType : Dimension> Measurement<UnitType>.convert(otherUnit: UnitType) {
            val newValue = converted(otherUnit)
            value = newValue.value
            unit = newValue.unit
        }
    }

    var unit: @UnsafeVariance UnitType = unit
        private set
}

val <UnitType : Dimension> Measurement<UnitType>.description get() = "$value ${unit.symbol}"

fun <UnitType : Dimension> Measurement<UnitType>.converted(otherUnit: UnitType): Measurement<UnitType> {
    return if (unit == otherUnit) {
        Measurement(value, otherUnit)
    } else {
        val valueInTermsOfBase = unit.converter.baseUnitValue(value)
        if (otherUnit == otherUnit.baseUnit()) {
            Measurement(valueInTermsOfBase, otherUnit)
        } else {
            val otherValueFromTermsOfBase = otherUnit.converter.value(valueInTermsOfBase)
            Measurement(otherValueFromTermsOfBase, otherUnit)
        }
    }
}

operator fun <UnitType : Dimension> Measurement<UnitType>.plus(other: Measurement<UnitType>): Measurement<UnitType> {
    return if (other.unit.javaClass == this.unit.javaClass) {
        if (other.unit == unit) {
            Measurement(value + other.value, unit)
        } else {
            val lhsValueInTermsOfBase = unit.converter.baseUnitValue(value)
            val otherValueInTermsOfBase = other.unit.converter.baseUnitValue(other.value)
            Measurement(lhsValueInTermsOfBase + otherValueInTermsOfBase, unit.baseUnit())
        }
    } else {
        throw Exception("Attempt to add measurements with non-equal units: ${unit.symbol} and ${other.unit.symbol}")
    }
}

operator fun <UnitType : Dimension> Measurement<UnitType>.minus(other: Measurement<UnitType>): Measurement<UnitType> {
    return if (other.unit.javaClass == this.unit.javaClass) {
        if (other.unit == unit) {
            Measurement(value - other.value, unit)
        } else {
            val lhsValueInTermsOfBase = unit.converter.baseUnitValue(value)
            val otherValueInTermsOfBase = other.unit.converter.baseUnitValue(other.value)
            Measurement(lhsValueInTermsOfBase - otherValueInTermsOfBase, unit.baseUnit())
        }
    } else {
        throw Exception("Attempt to add measurements with non-equal units: ${unit.symbol} and ${other.unit.symbol}")
    }
}

operator fun <UnitType : Dimension> Measurement<UnitType>.div(other: Measurement<UnitType>): Measurement<UnitType> {
    return if (other.unit.javaClass == this.unit.javaClass) {
        if (other.unit == unit) {
            Measurement(value / other.value, unit)
        } else {
            val lhsValueInTermsOfBase = unit.converter.baseUnitValue(value)
            val otherValueInTermsOfBase = other.unit.converter.baseUnitValue(other.value)
            Measurement(lhsValueInTermsOfBase / otherValueInTermsOfBase, unit.baseUnit())
        }
    } else {
        throw Exception("Attempt to add measurements with non-equal units: ${unit.symbol} and ${other.unit.symbol}")
    }
}

operator fun <UnitType : Dimension> Measurement<UnitType>.times(other: Measurement<UnitType>): Measurement<UnitType> {
    return if (other.unit.javaClass == this.unit.javaClass) {
        if (other.unit == unit) {
            Measurement(value * other.value, unit)
        } else {
            val lhsValueInTermsOfBase = unit.converter.baseUnitValue(value)
            val otherValueInTermsOfBase = other.unit.converter.baseUnitValue(other.value)
            Measurement(lhsValueInTermsOfBase * otherValueInTermsOfBase, unit.baseUnit())
        }
    } else {
        throw Exception("Attempt to add measurements with non-equal units: ${unit.symbol} and ${other.unit.symbol}")
    }
}
