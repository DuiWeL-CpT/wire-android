/**
 * Wire
 * Copyright (C) 2018 Wire Swiss GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.waz.zclient.common.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.waz.ZLog.ImplicitTag.implicitLogTag
import com.waz.utils.returning
import com.waz.zclient.{R, ViewHelper}
import com.waz.zclient.ui.text.{GlyphTextView, TypefaceTextView}
import com.waz.zclient.utils.ContextUtils

class MenuRowButton(context: Context, attrs: AttributeSet, style: Int) extends RelativeLayout(context, attrs, style) {
  def this(context: Context, attrs: AttributeSet) = this(context, attrs, 0)
  def this(context: Context) = this(context, null, 0)

  private implicit val ctx = context

  ViewHelper.inflate[View](R.layout.menu_row_button, ViewHelper.viewGroup(this), addToParent = true)

  private val attributesArray: TypedArray =
    context.getTheme.obtainStyledAttributes(attrs, R.styleable.MenuRowButton, 0, 0)

  private val textAttr = Option(attributesArray.getString(R.styleable.MenuRowButton_buttonText))
  private val iconAttr = Option(attributesArray.getString(R.styleable.MenuRowButton_buttonIcon))
  private val colorAttr = Option(attributesArray.getColorStateList(R.styleable.MenuRowButton_buttonTextColor))

  val text: TypefaceTextView = returning(findViewById[TypefaceTextView](R.id.text)){ text =>
    textAttr.foreach(text.setText)
    colorAttr.foreach(text.setTextColor)
  }
  val icon: GlyphTextView = returning(findViewById[GlyphTextView](R.id.icon)) { icon =>
    iconAttr.foreach(icon.setText)
    colorAttr.foreach(icon.setTextColor)
  }
  val divider: View = findViewById[View](R.id.divider)

  setBackground(ContextUtils.getDrawable(R.drawable.selector__transparent_button))
  setClickable(true)
  setFocusable(true)
}
