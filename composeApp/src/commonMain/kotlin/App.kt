import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import design.AsyncImage
import design.Container
import design.Loading
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.initialize
import theme.Inter
import theme.charcoal
import theme.grey400


private val options = FirebaseOptions(
    applicationId = CLRDConfig.APPLICATION_ID,
    apiKey = CLRDConfig.API_KEY,
    projectId = "clrd-demo"
)
private val store = Firebase.firestore(Firebase.initialize(options = options))

@Composable
fun App(userId: String){

    var isLoading by remember { mutableStateOf(true) }
    var dispatcher by remember { mutableStateOf<Dispatcher?>(null) }

    LaunchedEffect(userId){
        println("User: $userId")
        dispatcher = store.collection("dispatcher").document(userId).get().data<Dispatcher>()
        println("Dis: $dispatcher")
        isLoading = false
    }

    if (isLoading){
        Box(modifier = Modifier.fillMaxSize()) {
            Loading(Modifier.align(Alignment.Center))
        }
    }else{
        Container(enableScroll = true) {
            if (dispatcher != null){
                DispatcherDetails(dispatcher!!)
            }
        }
    }


}

@Composable
fun DispatcherDetails(dispatcher: Dispatcher){
    DataDivider("Basic")
    ImageAndName(image = dispatcher.passport!!, name = dispatcher.fullName!!, userId = dispatcher.userId)

    DataDivider("Contact")
    BioData(name = "Address", icon = Icons.Filled.LocationOn, value = dispatcher.address.orEmpty())
    BioData(name = "Call", icon = Icons.Filled.Call, value = dispatcher.phoneNumber.orEmpty())
    BioData(name = "Whatsapp", icon = Icons.Filled.Message, value = dispatcher.whatsappNumber.orEmpty())

    DataDivider("Official Government Documents")
    BioData(name = "NIN", icon = Icons.Filled.Tag, value = dispatcher.nin.orEmpty())
    BioData(name = "BVN", icon = Icons.Filled.Tag, value = dispatcher.bvn.orEmpty())
    ImageAndName(image = dispatcher.ninSlip.orEmpty(), name = "NIN Slip")
    ImageAndName(image = dispatcher.riderCard.orEmpty(), name = "Rider Card")
    ImageAndName(image = dispatcher.driverLicense.orEmpty(), name = "Driver License")

    DataDivider("Company")
    BioData(name = "Name", icon = Icons.Filled.Store, value = dispatcher.companyName.orEmpty())
    BioData(name = "Address", icon = Icons.Filled.Store, value = dispatcher.companyAddress.orEmpty())
    BioData(name = "RC number", icon = Icons.Filled.Store, value = dispatcher.companyRcNumber.orEmpty())

    DataDivider("Bike")
    BioData(name = "Brand", icon = Icons.Filled.TwoWheeler, value = dispatcher.bikeBrand.orEmpty())
    BioData(name = "Plate number", icon = Icons.Filled.TwoWheeler, value = dispatcher.bikePlateNumber.orEmpty())
    BioData(name = "Chases", icon = Icons.Filled.TwoWheeler, value = dispatcher.bikeChases.orEmpty())




}


@Composable
private fun ImageAndName(
    modifier: Modifier = Modifier,
    image: String,
    name: String,
    userId: String
){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier.size(250.dp),
            shape = RoundedCornerShape(5.dp),
            imageLink = image
        )
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = charcoal
        )
        Text(
            text = userId,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = charcoal.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun ImageAndName(
    modifier: Modifier = Modifier,
    image: String,
    name: String,
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = charcoal
        )
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            imageLink = image
        )
    }
}


@Composable
private fun BioData(
    name: String,
    icon: ImageVector,
    value: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.height(50.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(50.dp).border(width = (1.5).dp, charcoal.copy(alpha = 0.7f), RoundedCornerShape(5.dp)).padding(10.dp),
            imageVector = icon,
            contentDescription = null,
            tint = charcoal.copy(alpha = 0.7f)
        )
        Column(verticalArrangement = Arrangement.Top)  {
            Text(
                text = name,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = charcoal.copy(alpha = 0.6f)
                )
            )
            Text(
                text = value,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = charcoal
                )
            )
        }
    }
}

@Composable
private fun DataDivider(
    title: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(grey400.copy(0.1f), RoundedCornerShape(4.dp))
            .padding(vertical = 5.dp, horizontal = 10.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = charcoal.copy(alpha = 0.6f)
            )
        )
    }
}