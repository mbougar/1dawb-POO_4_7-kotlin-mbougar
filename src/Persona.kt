
/**
 * Clase que representa a una persona con su DNI y sus cuentas bancarias.
 * @property dni El número de identificación de la persona.
 * @property cuentas Un array de cuentas bancarias asociadas a la persona.
 */
class Persona(dni: String) {
    val cuentas = arrayOfNulls<Cuenta>(3)

    var dni: String = dni
        set(value) {
            require(value.matches(Regex("""^\d{8}[A-Z]$"""))) { "DNI inválido" }
            field = value
        }

    /**
     * Agrega una cuenta bancaria a la persona.
     * @param cuenta La cuenta bancaria a agregar.
     * @return true si la cuenta se agregó con éxito, false si la cuenta ya estaba presente o no había espacio disponible.
     */
    fun agregarCuenta(cuenta: Cuenta): Boolean {

        if (cuenta !in cuentas) {

            for (i in cuentas.indices) {

                if (cuentas[i] == null) {

                    cuentas[i] = cuenta
                    return true
                }
            }
        }
        return false
    }

    /**
     * Verifica si la persona es morosa (tiene alguna cuenta con saldo negativo).
     * @return true si la persona es morosa, false si no lo es.
     */
    fun esMorosa(): Boolean {
        for (i in cuentas.indices) {

            if ((cuentas[i]?.consultarSaldo()?: 0.0) < 0) {

                return true
            }
        }
        return false
    }

    /**
     * Realiza una transferencia de dinero entre cuentas de dos personas.
     * @param destinatario La persona a la que se le transferirá el dinero.
     * @param cuentaRemitente La cuenta bancaria desde la que se transferirá el dinero.
     * @param cuentaDestinatario La cuenta bancaria a la que se transferirá el dinero.
     * @param monto El monto a transferir.
     * @return true si la transferencia se realizó con éxito, false si alguna de las cuentas no está asociada a la persona correspondiente.
     */
    fun realizarTransferencia(destinatario: Persona, cuentaRemitente: Cuenta, cuentaDestinatario: Cuenta, monto: Double): Boolean {

        if (cuentaRemitente in this.cuentas && cuentaDestinatario in destinatario.cuentas) {
            for (i in cuentas.indices) {

                for (j in destinatario.cuentas.indices) {

                    if (this.cuentas[i] == cuentaRemitente && destinatario.cuentas[j] == cuentaDestinatario) {

                        this.cuentas[i]!!.realizarPago(monto)
                        destinatario.cuentas[j]!!.recibirAbono(monto)
                    }
                }
            }
        } else {
            return false
        }
        return true
    }

    /**
     * Retorna una representación en forma de cadena de la persona, incluyendo sus cuentas bancarias y su estado de morosidad.
     * @return Una cadena que representa a la persona.
     */
    override fun toString(): String {
        return "La persona${if (esMorosa()) " " else " no "} es morosa.\n - ${cuentas[0]}\n" +
                " - ${cuentas[1]}\n" +
                " - ${cuentas[2]}"
    }
}