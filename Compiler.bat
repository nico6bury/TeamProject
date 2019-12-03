echo %CD%
if not exist %CD%\compiledFiles\board\ mkdir %CD%\compiledFiles\board\
if not exist %CD%\compiledFiles\game\ mkdir %CD%\compiledFiles\game\
if not exist %CD%\compiledFiles\pieces\ mkdir %CD%\compiledFiles\pieces\
if not exist %CD%\compiledFiles\data\ mkdir %CD%\compiledFiles\data\

javac -d compiledFiles\ "src\board\Board.java" -classpath "src"
javac -d compiledFiles\ "src\board\GameFrame.java" -classpath "src"
javac -d compiledFiles\ "src\board\Menu.java" -classpath "src"
javac -d compiledFiles\ "src\board\Point.java" -classpath "src"
javac -d compiledFiles\ "src\board\ScorePanel.java" -classpath "src"

javac -d compiledFiles\ "src\data\Score.java" -classpath "src"
javac -d compiledFiles\ "src\data\DataIO.java" -classpath "src"
javac -d compiledFiles\ "src\data\Options.java" -classpath "src"

javac -d compiledFiles\ "src\game\TetrisApp.java" -classpath "src"

javac -d compiledFiles\ "src\pieces\GenericPiece.java" -classpath "src"
javac -d compiledFiles\ "src\pieces\JPiece.java" -classpath "src"
javac -d compiledFiles\ "src\pieces\LPiece.java" -classpath "src"
javac -d compiledFiles\ "src\pieces\SPiece.java" -classpath "src"
javac -d compiledFiles\ "src\pieces\SquarePiece.java" -classpath "src"
javac -d compiledFiles\ "src\pieces\StickPiece.java" -classpath "src"
javac -d compiledFiles\ "src\pieces\TPiece.java" -classpath "src"
javac -d compiledFiles\ "src\pieces\ZPiece.java" -classpath "src"

CD %CD%\compiledFiles

java "game.TetrisApp"
PAUSE