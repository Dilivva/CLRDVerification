import kotlinx.serialization.Serializable

@Serializable
data class Dispatcher(
    val userId: String = "",
    val email: String = "",

    val fullName: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val whatsappNumber: String? = null,

    val nin: String? = null,
    val ninSlip: String? = null,
    val bvn: String? = null,
    val riderCard: String? = null,
    val driverLicense: String? = null,
    val passport: String? = null,

    val companyName: String? = null,
    val companyAddress: String? = null,
    val companyRcNumber: String? = null,

    val bikeBrand: String? = null,
    val bikePlateNumber: String? = null,
    val bikeChases: String? = null,

    val solo: Boolean = false

){

    companion object{
        fun initDispatcher(userId: String, fullName: String?, email: String): Dispatcher{
            return Dispatcher(
                userId = userId,
                fullName = fullName,
                email = email,
                address = null,
                phoneNumber = null,
                whatsappNumber = null,
                nin = null,
                ninSlip = null,
                bvn = null,
                riderCard = null,
                driverLicense = null,
                passport = null,
                companyName = null,
                companyAddress = null,
                companyRcNumber = null,
                bikeBrand = null,
                bikePlateNumber = null,
                bikeChases = null
            )
        }
    }
}


 fun Dispatcher.isBioCaptured() = email.isNotEmpty() &&
     !fullName.isNullOrEmpty() &&
     !address.isNullOrEmpty() &&
     !phoneNumber.isNullOrEmpty() &&
     !whatsappNumber.isNullOrEmpty()

 fun Dispatcher.isGovtCaptured() = !nin.isNullOrEmpty() &&
     !ninSlip.isNullOrEmpty() &&
     !bvn.isNullOrEmpty() &&
     !riderCard.isNullOrEmpty() &&
     !driverLicense.isNullOrEmpty() &&
     !passport.isNullOrEmpty()

 fun Dispatcher.isCompanyCaptured() = solo ||
         (!companyName.isNullOrEmpty() &&
     !companyAddress.isNullOrEmpty() &&
     !companyRcNumber.isNullOrEmpty() )

 fun Dispatcher.isBikeCaptured() = !bikeBrand.isNullOrEmpty() &&
     !bikePlateNumber.isNullOrEmpty() &&
     !bikeChases.isNullOrEmpty()

 fun Dispatcher.isAllCaptured() = isBioCaptured() && isGovtCaptured() && isCompanyCaptured() && isBikeCaptured()