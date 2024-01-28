fun main() {

    val persona1 = Persona("12345678A")
    persona1.agregarCuenta(Cuenta("12345678901234567800", 0.0))
    persona1.agregarCuenta(Cuenta("92345678555234564770", 700.0))

    println(persona1)

    println("**************")

    persona1.cuentas[0]?.recibirAbono(1100.0)
    persona1.cuentas[1]?.realizarPago(750.0)

    println(persona1)

    println("**************")

    val persona2 = Persona("12345678B")
    persona2.agregarCuenta(Cuenta("54321678901264569523", 10000.0))
    persona2.realizarTransferencia(persona1, persona2.cuentas[0]!!, persona1.cuentas[1]!!, 1000.0 )

    println(persona1)
    println(persona2)
}