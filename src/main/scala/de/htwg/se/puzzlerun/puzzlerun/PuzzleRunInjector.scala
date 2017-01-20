/**
 * Created by kmg on 19.01.17.
 */
package de.htwg.se.puzzlerun.puzzlerun

import com.google.inject.{ AbstractModule, PrivateModule }
import de.htwg.se.puzzlerun.controller.IController
import de.htwg.se.puzzlerun.controller.Impl1.Controller
import net.codingwell.scalaguice.{ ScalaModule, ScalaPrivateModule }

class PuzzleRunInjector extends AbstractModule with ScalaModule {
  def configure(): Unit = {

    bind[IController].to[Controller]

  }
}