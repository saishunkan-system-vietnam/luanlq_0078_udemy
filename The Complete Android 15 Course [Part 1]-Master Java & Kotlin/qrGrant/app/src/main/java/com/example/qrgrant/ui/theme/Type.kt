package com.example.qrgrant.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.qrgrant.R

val NotoSansJP = FontFamily(
    Font(R.font.noto_sans_jp_thin, FontWeight.Thin),
    Font(R.font.noto_sans_jp_light, FontWeight.Light),
    Font(R.font.noto_sans_jp_regular, FontWeight.Normal),
    Font(R.font.noto_sans_jp_medium, FontWeight.Medium),
    Font(R.font.noto_sans_jp_bold, FontWeight.Bold),
    Font(R.font.noto_sans_jp_black, FontWeight.Black),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Black,
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ), displayMedium = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ), displaySmall = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ), headlineLarge = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ), headlineMedium = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ), headlineSmall = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ), bodyLarge = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ), bodyMedium = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ), bodySmall = TextStyle(
        fontFamily = NotoSansJP, fontWeight = FontWeight.Light, fontSize = 12.sp, lineHeight = 18.sp
    ), labelLarge = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ), labelMedium = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ), labelSmall = TextStyle(
        fontFamily = NotoSansJP, fontWeight = FontWeight.Thin, fontSize = 10.sp, lineHeight = 14.sp
    )
)


object PrimaryStyle {
    private val fontFamily = NotoSansJP

    fun bold(size: Int = 14, color: Color = Color.Unspecified, lineHeight: Int? = null): TextStyle {
        return TextStyle(
            fontSize = size.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = color,
            lineHeight = (lineHeight ?: size).sp
        )
    }

    fun medium(
        size: Int = 14,
        color: Color = Color.Unspecified,
        lineHeight: Int? = null,
        spacing: Float? = null,
        textAlign: TextAlign? = null
    ): TextStyle {
        return TextStyle(
            fontSize = size.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            color = color,
            lineHeight = (lineHeight ?: size).sp,
            letterSpacing = spacing?.sp ?: 0.sp,
            textAlign = textAlign ?: TextAlign.Start
        )
    }

    fun normal(
        size: Int = 14,
        color: Color = Color.Unspecified,
        lineHeight: Int? = null,
        spacing: Float? = null
    ): TextStyle {
        return TextStyle(
            fontSize = size.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            color = color,
            lineHeight = (lineHeight ?: size).sp,
            letterSpacing = spacing?.sp ?: 0.sp
        )
    }

    fun regular(
        size: Int = 14,
        color: Color = Color.Unspecified,
        lineHeight: Int? = null,
        spacing: Float? = null
    ): TextStyle {
        return TextStyle(
            fontSize = size.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            color = color,
            lineHeight = (lineHeight ?: size).sp,
            letterSpacing = spacing?.sp ?: 0.sp
        )
    }

    fun light(
        size: Int = 14,
        color: Color = Color.Unspecified,
        lineHeight: Int? = null,
        spacing: Float? = null
    ): TextStyle {
        return TextStyle(
            fontSize = size.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Light,
            color = color,
            lineHeight = (lineHeight ?: size).sp,
            letterSpacing = spacing?.sp ?: 0.sp
        )
    }

    val error: TextStyle
        get() = TextStyle(
            fontSize = 17.sp, color = Color.Red, fontFamily = fontFamily
        )
}