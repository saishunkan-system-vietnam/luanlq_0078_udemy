package com.example.qrgrant.ui.components.input

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrgrant.R
import android.util.Log
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

@Composable
fun InputForm(
    modifier: Modifier = Modifier,
    text: String,
    title: String = "",
    hint: String = "",
    textError: String = "",
    onTextChanged: (String) -> Unit,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    isPassword: Boolean = false,
    readOnly: Boolean = false,
    isDisable: Boolean = false,
    enabled: Boolean = true,
    cornerRadius: Dp = 90.dp,
    backgroundColor: Color = colorResource(R.color.background_input),
    focusedBorderColor: Color = colorResource(R.color.primary),
    unfocusedBorderColor: Color = Color.Gray,
    textAlign: TextAlign = TextAlign.Start,
    keyboardType: KeyboardType? = null,
    patterns: String? = null,
    maxLength: Int? = null,
    onClick: (() -> Unit)? = null,
) {

    Log.d("textError", textError)
    if (title.isNotEmpty()) Text(title, fontSize = 16.sp)

    PrimaryInput(
        text = text,
        onTextChanged = onTextChanged,
        hint = hint,
        startIcon = startIcon,
        endIcon = endIcon,
        isPassword = isPassword,
        modifier = modifier,
        cornerRadius = cornerRadius,
        readOnly = readOnly,
        isDisable = isDisable,
        enabled = enabled,
        backgroundColor = backgroundColor,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        textAlign = textAlign,
        keyboardType = keyboardType,
        patterns = patterns,
        maxLength = maxLength,
        onClick = onClick
    )

    if (textError.isNotEmpty()) {
        Spacer(modifier = Modifier.height(3.dp))
        Text(textError, fontSize = 14.sp, color = MaterialTheme.colorScheme.error)
    }
}