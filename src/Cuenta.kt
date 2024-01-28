
/**
 * Clase que representa una cuenta bancaria.
 * @property numeroCuenta El número de cuenta asociado a la cuenta.
 * @property saldoDisponible El saldo disponible en la cuenta.
 */
class Cuenta(val numeroCuenta: String, private var saldoDisponible: Double = 0.0) {

    /**
     * Consulta el saldo disponible en la cuenta.
     * @return El saldo disponible en la cuenta.
     */
    fun consultarSaldo(): Double {
        return saldoDisponible
    }

    /**
     * Recibe un abono en la cuenta.
     * @param monto El monto del abono.
     * @return true si el abono se realizó con éxito, false si el monto no es positivo.
     */
    fun recibirAbono(monto: Double): Boolean {
        if (monto > 0) {
            saldoDisponible += monto
            return true
        }
        return false
    }

    /**
     * Realiza un pago desde la cuenta.
     * @param monto El monto del pago.
     * @return true si el pago se realizó con éxito, false si el monto no es positivo.
     */
    fun realizarPago(monto: Double): Boolean {
        if (monto > 0) {
            saldoDisponible -= monto
            return true
        }
        return false
    }

    /**
     * Retorna una representación en forma de cadena de la cuenta, incluyendo su número y saldo.
     * @return Una cadena que representa la cuenta.
     */
    override fun toString(): String {
        return "Cuenta con número $numeroCuenta: %.2f€".format(saldoDisponible)
    }
}