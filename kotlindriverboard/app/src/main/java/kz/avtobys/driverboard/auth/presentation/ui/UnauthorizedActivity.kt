package kz.avtobys.driverboard.auth.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.avtobys.core.presentation.ext.empty
import kz.avtobys.core.presentation.ui.theme.KotlindriverboardTheme
import kz.avtobys.core.presentation.ui.theme.MontserratTypography
import kz.avtobys.core.presentation.ui.theme.TextFieldTypography
import kz.avtobys.core.presentation.ui.theme.primaryColor
import kz.avtobys.driverboard.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class UnauthorizedActivity : ComponentActivity() {

    private val viewModel by viewModel<UnauthorizedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlindriverboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(
                                state = rememberScrollState(),
                                enabled = true,
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        AppIcon()
                        LoginForm(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun AppIcon() {
    Spacer(modifier = Modifier.height(30.dp))
    Image(
        painter = painterResource(id = R.drawable.ic_avtobys),
        contentDescription = String.empty,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .width(120.dp)
            .height(50.dp),
    )
    Spacer(modifier = Modifier.height(30.dp))
    Text(
        text = stringResource(id = R.string.auth_title),
        style = MontserratTypography.body2,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun LoginForm(viewModel: UnauthorizedViewModel? = null) {
    var transportNumberText by remember {
        mutableStateOf(viewModel?.plateNumber?.value.orEmpty())
    }
    Spacer(modifier = Modifier.height(25.dp))
    OutlinedTextField(
        value = transportNumberText,
        label = {
            Text(
                text = stringResource(id = R.string.auth_plate_number_title),
                color = primaryColor,
            )
        },
        modifier = Modifier.fillMaxWidth(0.4f),
        onValueChange = { transportNumberText = it },
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = { transportNumberText = String.empty }
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = String.empty,
                )
            }
        },
        textStyle = TextFieldTypography.body1,
    )
    Spacer(modifier = Modifier.height(15.dp))
    var loginText by remember {
        mutableStateOf(String.empty)
    }
    OutlinedTextField(
        value = loginText,
        label = {
            Text(
                text = stringResource(id = R.string.auth_user_name_title),
                color = primaryColor,
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth(0.4f),
        onValueChange = { loginText = it },
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = { loginText = String.empty }
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = String.empty,
                )
            }
        },
        textStyle = TextFieldTypography.body1,
    )
    Spacer(modifier = Modifier.height(15.dp))
    var passwordText by remember {
        mutableStateOf(String.empty)
    }
    OutlinedTextField(
        value = passwordText,
        label = {
            Text(
                text = stringResource(id = R.string.auth_password_title),
                color = primaryColor,
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth(0.4f),
        onValueChange = { passwordText = it },
        trailingIcon = {
            IconButton(
                onClick = { passwordText = String.empty }
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = String.empty,
                )
            }
        },
        textStyle = TextFieldTypography.body1,
    )
    Spacer(modifier = Modifier.height(20.dp))
    Box {
        Button(
            onClick = {
                viewModel?.onLoginClick(
                    userName = loginText,
                    password = passwordText,
                    plateNumber = transportNumberText,
                )
            },
            modifier = Modifier
                .fillMaxWidth(0.4f),
            shape = CircleShape,
        ) {
            Text(
                text = stringResource(id = R.string.auth_login_button_title),
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                style = TextFieldTypography.body2,
                modifier = Modifier.padding(vertical = 10.dp),
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.TABLET,
)
@Composable
fun GreetingPreview() {
    KotlindriverboardTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            AppIcon()
            LoginForm()
        }
    }
}