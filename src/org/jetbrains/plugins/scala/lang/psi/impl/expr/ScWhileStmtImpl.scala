package org.jetbrains.plugins.scala
package lang
package psi
package impl
package expr

import _root_.org.jetbrains.plugins.scala.lang.psi.types.ScType
import org.jetbrains.plugins.scala.lang.lexer.ScalaTokenTypes
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.plugins.scala.lang.psi.api.expr._
import types.result.{TypingContext, Success}
import com.intellij.psi.{PsiElement, PsiElementVisitor}
import api.ScalaElementVisitor

/**
* @author Alexander.Podkhalyuzin
*/

class ScWhileStmtImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with ScWhileStmt {
  override def accept(visitor: PsiElementVisitor): Unit = {
    visitor match {
      case visitor: ScalaElementVisitor => super.accept(visitor)
      case _ => super.accept(visitor)
    }
  }

  override def toString: String = "WhileStatement"

  def condition = {
    val rpar = findChildByType(ScalaTokenTypes.tRPARENTHESIS)
    val c = if (rpar != null) PsiTreeUtil.getPrevSiblingOfType(rpar, classOf[ScExpression]) else null
    if (c == null) None else Some(c)
  }

  def body = {
    val rpar = findChildByType(ScalaTokenTypes.tRPARENTHESIS)
    val c = if (rpar != null) PsiTreeUtil.getNextSiblingOfType(rpar, classOf[ScExpression]) else null
    if (c == null) None else Some(c)
  }

  def getLeftParenthesis = {
    val leftParenthesis = findChildByType(ScalaTokenTypes.tLPARENTHESIS)
    if (leftParenthesis == null) None else Some(leftParenthesis)
  }

  def getRightParenthesis = {
    val rightParenthesis = findChildByType(ScalaTokenTypes.tRPARENTHESIS)
    if (rightParenthesis == null) None else Some(rightParenthesis)
  }



  protected override def innerType(ctx: TypingContext) = Success(types.Unit, Some(this))
}