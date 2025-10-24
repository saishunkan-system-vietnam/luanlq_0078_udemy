package com.example.qrgrant.ui.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrgrant.R
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun PrimaryInput(
    title: String? = null,
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    isPassword: Boolean = false,
    readOnly: Boolean = false,
    isDisable: Boolean = false,
    enabled: Boolean = true,
    backgroundColor: Color = colorResource(R.color.background_input),
    cornerRadius: Dp = 30.dp,
    focusedBorderColor: Color = colorResource(R.color.primary),
    unfocusedBorderColor: Color = Color.Gray,
    textAlign: TextAlign = TextAlign.Start,
    keyboardType: KeyboardType? = null,
    patterns: String? = null,
    maxLength: Int? = null,
    onClick: (() -> Unit)? = null,
) {
    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = { input ->
            var filtered = patterns?.let { pattern ->
                val regex = Regex(pattern)
                input.filter { it.toString().matches(regex) }
            } ?: input

            if (maxLength != null && filtered.length > maxLength)
                filtered = filtered.take(maxLength)

            onTextChanged(filtered)
        },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
            .then(
                if (onClick != null && !isDisable)
                    Modifier.clickable { onClick() }
                else
                    Modifier
            ),
        label = title?.let { { Text(it, fontSize = 14.sp) } },
        placeholder = { Text(hint, color = Color.Gray, fontSize = 14.sp) },
        readOnly = readOnly || onClick != null,
        enabled = enabled,
        singleLine = true,
        textStyle = PrimaryStyle.medium(
            color = colorResource(if (!isDisable) R.color.body_text else R.color.border_color),
            textAlign = textAlign
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType ?: KeyboardType.Text),
        leadingIcon = startIcon?.let {
            { Icon(it, contentDescription = null, tint = colorResource(R.color.primary)) }
        },
        trailingIcon = if (isPassword || endIcon != null) {
            {
                when {
                    isPassword -> {
                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(
                                imageVector = if (passwordVisible.value)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff,
                                contentDescription = null,
                                tint = colorResource(R.color.primary)
                            )
                        }
                    }

                    endIcon != null -> {
                        Icon(
                            imageVector = endIcon,
                            contentDescription = null,
                            tint = colorResource(R.color.primary)
                        )
                    }
                }
            }
        } else null,
        visualTransformation = if (isPassword && !passwordVisible.value)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
            disabledBorderColor = Color.LightGray
        ),
        shape = RoundedCornerShape(cornerRadius)
    )
}
