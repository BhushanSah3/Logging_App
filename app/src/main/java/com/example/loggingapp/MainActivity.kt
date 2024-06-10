package com.example.loggingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.loggingapp.ui.theme.LoggingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoggingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Loginui()
                }
            }
        }
    }
}

//@Preview(showBackground = True)
@Composable
fun Loginui() {

    val email = remember { mutableStateOf("") }    // this is required to update right after right
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current

    val showPassword = remember { mutableStateOf(false) }
    val emailError = remember { mutableStateOf("") }
    val passwordError = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(   // gradient
                    colors = listOf(Color(0xFF1E3C72), Color(0xFF2A5298))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally  // for all the components inside th ecolumn the alignment is horizonbtally
        ) {

            Spacer(modifier = Modifier.height(40.dp)) // to addd spaces

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "App Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Welcome! Ladies and Gentlemen",
                fontSize = 26.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                    emailError.value = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholder = { Text(text = "E-mail") },  // this to display inside of the email
                isError = emailError.value.isNotEmpty(),
                shape = RoundedCornerShape(8.dp)   /// rounded courners
            )
//            If emailError.value is not empty,
//            isError will be true, which triggers the OutlinedTextField to show an error state
            
//            The if (emailError.value.isNotEmpty()) block checks if there is an error message to be displayed.
//            If emailError.value is not empty,
//            the Text composable below the OutlinedTextField will display the error message in red.
            if (emailError.value.isNotEmpty()) {  //
                Text(   /// error message if the input is wrong right below instead of toast message
                    text = emailError.value,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    //this padding is for the message that will show up in red
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = password.value,
                onValueChange = {
                    password.value = it
                    passwordError.value = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholder = { Text(text = "Password") },

                visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                isError = passwordError.value.isNotEmpty(),
                shape = RoundedCornerShape(8.dp),

                trailingIcon = {
                    val image =           // showing password or not
                        if (showPassword.value) painterResource(id = R.drawable.onvisi)
                        else painterResource(id = R.drawable.offvisi)

                    IconButton(onClick = { showPassword.value = !showPassword.value }) {
                        Icon(painter = image, contentDescription = null)
                    }
                })

            if (passwordError.value.isNotEmpty()) {
                Text(
                    text = passwordError.value,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = {
                    if (email.value.isEmpty() || password.value.isEmpty()) {
                        if (email.value.isEmpty()) {
                            emailError.value = "Email cannot be empty"
                        }
                        if (password.value.isEmpty()) {
                            passwordError.value = "Password cannot be empty"
                        }
                    } else if (!email.value.contains("@")) {
                        emailError.value = "Email must contain @"
                    }else if(!email.value.contains(".com")) {
                            emailError.value = "Email must contain .com"
                    }
                    else {
                        Toast.makeText(context, "Login in Successfully", Toast.LENGTH_SHORT).show()
//                        OutlinedButton.setOnClickListener {
//                            val intent = Intent(this, SecondActivity::class.java).also
//                            startActivity(Intent(context, SecondActivity::class.java))
//                        }
                        context.startActivity(Intent(context, SecondActivity::class.java))
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(
                        color = Color(0xFF1E88E5), shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(text = "Login", textAlign = TextAlign.Center, color = Color.White)
            }
        }
    }
}
  // this is for seeing preview as we used to see in the xml
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoggingAppTheme {
        Loginui()
    }
}

//Automatic Error State: When isError is true, the OutlinedTextField component automatically
//changes its border color to red and other styling elements to indicate an error.
//This behavior is predefined in the Compose
//Material library and does not require additional code to change the border color.
