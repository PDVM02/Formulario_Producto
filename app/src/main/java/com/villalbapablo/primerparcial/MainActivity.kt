package com.villalbapablo.primerparcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.villalbapablo.primerparcial.ui.theme.PrimerParcialTheme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrimerParcialTheme {
                FormularioProducto();
            }
        }
    }
}

@Composable
fun FormularioProducto() {
    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productCategory by remember { mutableStateOf("") }
    val category = listOf("Electrónica", "Ropa", "Alimentos")

    Column(modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        TextField(
            value = productName,
            onValueChange = { productName = it },
            label = {
                       Text(stringResource(R.string.label_product))
                    },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = productPrice,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    productPrice = it
                }
            },
            label = {
                       Text(stringResource(R.string.label_price_product))
                    },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Categoría del producto:")
        Spacer(modifier = Modifier.height(16.dp))
        category.forEach { category ->
            Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable { productCategory = category },
                verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (productCategory == category),
                    onClick = { productCategory = category }
                )
                Text(text = category,
                    modifier = Modifier.padding(start = 8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                        productName = ""
                        productPrice = ""
                        productCategory = ""
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioProductoPreview() {
    FormularioProducto()
}