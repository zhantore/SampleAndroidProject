package kz.avtobys.driverboard.auth.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kz.avtobys.driverboard.R

// Set of Material typography styles to start with
val MainTypography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
val MontserratTypography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(
            Font(resId = R.font.montserrat_regular, weight = FontWeight(380))
        ),
        fontSize = 25.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 30.sp,
        letterSpacing = 1.25.sp,
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(
            Font(resId = R.font.montserrat_bold, weight = FontWeight(380))
        ),
        fontSize = 25.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 30.sp,
        letterSpacing = 1.25.sp,
    ),
)

val TextFieldTypography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(
            Font(resId = R.font.montserrat_regular, weight = FontWeight(400))
        ),
        fontSize = 15.sp,
        fontWeight = FontWeight(400),
        lineHeight = 18.sp,
        letterSpacing = 1.25.sp,
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(
            Font(resId = R.font.montserrat_regular, weight = FontWeight(400))
        ),
        fontSize = 15.sp,
        fontWeight = FontWeight(700),
        lineHeight = 18.sp,
        letterSpacing = 1.25.sp,
    )
)

