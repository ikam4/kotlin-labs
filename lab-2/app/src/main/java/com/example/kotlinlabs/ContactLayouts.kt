package com.example.kotlinlabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class Contact(
    val firstName: String,
    val patronymic: String,
    val lastName: String,
    val phone: String,
    val address: String,
)

private val Contacts = listOf(
    Contact("Евгений", "Андреевич", "Лукашин", "+7 495 495 95 95", "г. Москва, 3-я улица Строителей, д. 25, кв. 12"),
    Contact("Василий", "Егорович", "Кузьмин", "—", "Московская область, дер. Крутово, д. 4"),
    Contact("Людмила", "Прокофьевна", "Калугина", "+7 495 788 78 78", "Москва, Большая Никитская, д. 43, кв. 290"),
)

@Composable
internal fun ContactColumn() {
    ContactDetails(Contacts.first(), modifier = Modifier.fillMaxWidth())
}

@Composable
internal fun ContactList() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        for (contact in Contacts) {
            ContactDetails(
                contact = contact,
                modifier = Modifier.fillMaxWidth().background(Color.LightGray).padding(8.dp),
                alignment = TextAlign.Center,
            )
        }
    }
}

@Composable
internal fun ContactRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ContactDetails(
            contact = Contacts.first(),
            modifier = Modifier.weight(1f),
            alignment = TextAlign.Center,
            addressAlignment = TextAlign.Start,
        )
        Image(
            painter = painterResource(R.drawable.contact_star),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
    }
}

@Composable
private fun ContactDetails(
    contact: Contact,
    modifier: Modifier = Modifier,
    alignment: TextAlign = TextAlign.Start,
    addressAlignment: TextAlign = alignment,
) {
    Column(modifier) {
        ContactLine(stringResource(R.string.contact_first_name, contact.firstName), alignment)
        ContactLine(stringResource(R.string.contact_patronymic, contact.patronymic), alignment)
        ContactLine(stringResource(R.string.contact_last_name, contact.lastName), alignment)
        ContactLine(stringResource(R.string.contact_phone, contact.phone), alignment)
        ContactLine(stringResource(R.string.contact_address, contact.address), addressAlignment)
    }
}

@Composable
private fun ContactLine(text: String, alignment: TextAlign) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Black,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        textAlign = alignment,
    )
}

@Composable
private fun ContactPreviewFrame(content: @Composable () -> Unit) {
    Column(Modifier.fillMaxSize().background(Color.White)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFF6200EE))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                text = "FirstComposeProject",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
        }
        content()
    }
}

@Preview(name = "Один контакт", showSystemUi = true)
@Composable
private fun ContactColumnPreview() {
    ContactPreviewFrame { ContactColumn() }
}

@Preview(name = "Список контактов", showSystemUi = true)
@Composable
private fun ContactListPreview() {
    ContactPreviewFrame { ContactList() }
}

@Preview(name = "Контакт со значком", showSystemUi = true)
@Composable
private fun ContactRowPreview() {
    ContactPreviewFrame { ContactRow() }
}
