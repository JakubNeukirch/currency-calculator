package pl.jakubneukirch.currencycalculator.usecase

import org.junit.Test
import pl.jakubneukirch.currencycalculator.base.BaseUseCaseTest

class ConvertValuesTest: BaseUseCaseTest<IConvertValues>() {
    override lateinit var useCase: IConvertValues

    override fun setup() {
        super.setup()
        useCase = ConvertValues()
    }

    @Test
    fun `should calculate currency values`(){
        
    }
}