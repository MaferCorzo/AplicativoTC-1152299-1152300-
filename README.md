Resumen: 


Se ha desarrollado una aplicación con un propósito singular: definir un lenguaje a partir de una gramática dada. Esta herramienta utiliza un conjunto diverso de recursos para generar palabras de manera eficiente y precisa, con el fin de establecer un lenguaje específico. Al analizar detalladamente la estructura gramatical proporcionada, el aplicativo emplea algoritmos recursivos y técnicas especializadas para identificar patrones lingüísticos y construir palabras coherentes que cumplan con las reglas gramaticales establecidas.
 


Como funciona a nivel de interfaz: 


Ingresar Variables No Terminales: 
Se deben ingresar las variables no terminales en el campo de texto "No Terminal" (txtNoTerminal).
Las variables no terminales deben ingresarse en mayúsculas y separadas por espacios.
Por ejemplo, si se tienen variables no terminales A, B y C, el usuario debe ingresarlas como "A B C".


Ingresar Variables Terminales:
Se  debe ingresar las variables terminales en el campo de texto "Terminal" (txtTerminal).
Las variables terminales también se deben ingresar en minúsculas y separadas por espacios.
Por ejemplo, si se tienen variables terminales a, b,c,% y 2, el usuario debe ingresarlas como "a b c % 2".


Ingresar Símbolo Inicial:
Se debe ingresar el símbolo inicial en el campo de texto "Inicial" (txtInicial).
Este símbolo debe ser una única variable no-Terminal y debe estar presente en las variables no terminales ingresadas previamente.


Ingresar Cantidad de palabras:
El usuario puede ingresar la cantidad de palabras según las reglas enviadas que permitirá darle efectividad al proceso de generación de palabras.


Ingresar Profundidad:
El usuario puede ingresar el número máximo de llamados recursivos que puede tener una palabra.


Guardar Gramática:
Una vez ingresado los datos de las variables no terminales, variables terminales, variable inicial, cantidad de palabras y profundidad el usuario debe darle clic en el botón “Guardar Gramática” para poder luego establecer las producciones por los datos ingresados.Seguidamente se mostrarán las variables no-Terminales en una lista en la parte inferior.


Ingresar Reglas de Producción:
Para cada variable no terminal ingresada, el usuario debe especificar las reglas de producción en los campos de texto de la lista.
Cada campo de texto en la lista corresponde a una variable no terminal y permite al usuario ingresar las reglas de producción asociadas a esa variable. Luego debe presionar el botón “Guardar Regla”.
Las reglas de producción deben seguir el formato estándar de una gramática formal.
Por ejemplo, si se tiene la variable no terminal A y sus reglas de producción son A -> ab / B, el usuario debe ingresar estas reglas en el campo de texto correspondiente a A como "a b B".
En caso de desear cambiar las reglas de alguna variable simplemente debe seleccionar nuevamente, ingresar las reglas actualizadas y volver a presionar el botón “Guardar Regla” . Deberá recibir un mensaje que confirme que las nuevas reglas a la variables se han guardado con éxito. Por otro lado, si el usuario cierra la ventana de confirmación sin seleccionar "OK" ni "Cancelar", las reglas anteriores se mantienen sin  ninguna modificación. 


Actualizar o Limpiar los Campos:
Si se comete un error o se desea restablecer los campos, el usuario puede hacer clic en el botón "Limpiar" (cdLimpiar) para limpiar todos los campos de texto y comenzar de nuevo.


Procesar o Visualizar el Resultado:
Una vez que todos los datos han sido ingresados correctamente, el usuario puede proceder a realizar acciones adicionales, como procesar la gramática formal o visualizar el resultado esperado sobre la definición del lenguaje, dando click en el botón “Definir Lenguaje” y asi mismo poder visualizar las variables inalcanzables,las variables no-Terminales que no se pueden derivar desde el símbolo inicial de la gramática o desde ninguna producción de las demás no-Terminales. También se podrá visualizar aquellas No-Terminales que tengan reglas inútiles.Gracias a las restricciones establecidas cada no-Terminal debe tener como mínimo un terminal en sus producciones, esto para evitar complicaciones en la recursividad. Por lo tanto, no habrían variables no-Terminales inútiles.




Limitaciones: 


Durante el desarrollo del aplicativo, se encontraron varias limitaciones que influyeron en su funcionamiento. Estas limitaciones se derivan principalmente de restricciones de rendimiento dependiendo de la máquina donde se ejecute el programa. Con consecuencia se optó por permitir que el usuario decida la cantidad de palabras y la cantidad máxima de llamados recursivos que puede hacer.


En primer lugar, se enfrentaron limitaciones inherentes a la capacidad de la máquina donde se ejecuta el aplicativo. Estas limitaciones podrían manifestarse en forma de restricciones de memoria, potencia de cálculo o capacidad de procesamiento, lo que podría afectar el rendimiento general del aplicativo.


Además, se estableció un límite en el número de palabras generadas por recursividad. Este límite se implementó para evitar posibles problemas de rendimiento o desbordamiento de memoria al ejecutar recursiones profundas. Al establecer un límite en la cantidad de palabras generadas por recursividad, se garantiza un funcionamiento más controlado y eficiente del aplicativo.


Otra limitación importante fue la profundidad máxima permitida para los bucles. Esto se implementó para prevenir la ejecución infinita de bucles recursivos, lo que podría ocurrir si no se estableciera un límite en la profundidad de los mismos. Al definir una profundidad máxima para los bucles, se asegura que el aplicativo no entre en estados de ejecución indefinida, lo que podría llevar a un consumo excesivo de recursos y a una ejecución ineficiente.


En conjunto, estas limitaciones resaltaron en el diseño y la implementación del aplicativo, pero fueron necesarias para garantizar un funcionamiento estable, eficiente y seguro en diferentes entornos y condiciones de uso.


Desarrollo del algoritmo:


Atributos: 
noTerminales: Una lista de símbolos no terminales en la gramática. 
terminales: Una lista de símbolos terminales en la gramática. 
inicial: El símbolo inicial de la gramática. 
tr: Un mapa que asigna a cada símbolo no terminal una lista de cadenas que representan sus producciones. 
palabras: Un conjunto para almacenar las palabras generadas. 
cantidad: Un contador que probablemente indica el número de palabras generadas. 
max: Un parámetro que parece estar relacionado con la profundidad máxima de las producciones.


Constructores:
Hay dos constructores, uno vacío y otro que toma argumentos para inicializar los atributos de la clase.
 
Metodo Recursiv(): 
Este método será el encargado de generar las palabras, a partir de la variable inicial.
El método recibe los siguientes parámetros: 
produc1: El mapa de reglas de producción de la gramática. 
noTerminales: La lista de no terminales de la gramática. 
variable: La variable actual que se está expandiendo. 
maxDepth: La profundidad máxima permitida para la expansión. 
processed: Un conjunto para rastrear las variables que ya se han procesado durante la expansión. 
Primero se hace una comprobación de si hemos alcanzado la profundidad máxima permitida. Si es así, devuelve una cadena vacía para detener la expansión y evitar bucles infinitos. 
Luego verificamos si la variable actual tiene no reglas asociadas en el HashMap , en tal caso significa que es una terminal y se devuelve directamente. 
Se obtienen las reglas de producción asociadas con la variable actual. Luego, se elige una regla de manera aleatoria de entre las disponibles. Esta regla determina cómo se expandirá la variable actual. 
Se crea un “StringBuilder” llamado “gramatica” para construir la palabra resultante. Se itera sobre cada carácter en la regla seleccionada. 
Para cada carácter en la regla seleccionada, se verifica si es un no terminal. Si lo es, se realiza una llamada recursiva al propio método, reduciendo la profundidad en cada llamada recursiva, y se agrega su resultado (la variable “result ”) al “ StringBuilder”. Si es un terminal, simplemente se agrega al StringBuilder. Aquí comienzan a formarse las palabras, gracias al parámetro tipo Set “processed” evitará que se guarden palabras repetidas. 
Finalmente, se devuelve la palabra construida en forma de cadena, que representa la expansión de la variable actual de acuerdo con las reglas de producción. 
 
Método  generadorDePalabras(int maxDepth, int cantidad): 


Este método se encarga de llamar al método “recursiv()”  para generar las palabras a partir de la gramática. La particularidad de este método es que permite establecer un límite para la cantidad de palabras y la profundidad máxima permitida para la expansión. Esto evita que el método “recursiv” se llame a sí mismo indefinidamente y entre en bucle. 


Método esTermina()l:
verifica si una letra dada es un terminal.
Itera sobre la lista de terminales y compara cada elemento con la letra dada.
Retorna true si la letra es un terminal, de lo contrario retorna false.


Método esNoTerminal():
verifica si una letra dada es un no terminal.
Itera sobre la lista de no terminales y compara cada elemento con la letra dada.
Retorna true si la letra es un no terminal, de lo contrario retorna false.


Método reglasConTerminal():
Verifica si una lista de producciones contiene al menos un terminal.
Itera sobre cada producción y verifica si alguna de ellas es un terminal llamando al método esTerminal.
Retorna true si hay al menos un terminal en las producciones, de lo contrario retorna false.


Método variableInutil():
Identifica y devuelve una lista de no terminales que tienen reglas inútiles.
Itera sobre cada no terminal y sus producciones.
Verifica si las producciones contienen al menos un terminal siempre o no terminal válido.
Si ninguna producción contiene símbolos válidos, agrega el no terminal a la lista de inútiles.


Método inalcanzable():
Identifica y devuelve una lista de no terminales que son inalcanzables desde el símbolo inicial.
Itera sobre cada no terminal y sus producciones.
Agrega a un conjunto los no terminales alcanzados desde el símbolo inicial.
Retorna los no terminales que no están en el conjunto de alcanzados.


Métodos getters y setters:
Permiten acceder y modificar los atributos privados de la clase.


Funcionamiento del Controller:
initialize(): Este método es invocado por el FXMLLoader después de que se ha cargado el archivo FXML y se han inicializado todos los elementos de la interfaz gráfica. Se asegura de que todos los elementos de la interfaz hayan sido inyectados correctamente y, en este caso, inicializa el objeto lenguaje. 
Actualizar(ActionEvent event): Este método se activa cuando se hace clic en el botón "Actualizar". Su función es limpiar todos los campos de entrada y salida en la interfaz gráfica, preparándola para una nueva entrada de datos. 
Guardar(ActionEvent event): Este método se activa cuando se hace clic en el botón "Guardar". Su objetivo es recopilar los datos ingresados por el usuario desde los campos de texto en la interfaz gráfica y asignarlos al objeto de gramática formal (lenguaje). También verifica la validez de los datos ingresados y muestra una alerta en caso de errores. 
mostrarNoTerminales(): Este método se encarga de mostrar la lista de símbolos no terminales en la interfaz gráfica, utilizando un ListView. Obtiene la lista de no terminales del objeto lenguaje y la muestra en la lista de la interfaz. 
GuardarRegla(ActionEvent event): Este método se activa cuando se hace clic en el botón "Guardar Regla". Su función es obtener la regla ingresada por el usuario para un no terminal seleccionado y asociarla con ese no terminal en el mapa de reglas (mapa). Antes de guardar la regla, verifica que al menos una regla contenga un terminal y maneja las actualizaciones de reglas existentes si ya hay una regla guardada para ese no terminal. 
abrirVentana2(ActionEvent event): Este método se activa cuando se hace clic en el botón "Generar Palabras". Su función es utilizar el objeto lenguaje para generar palabras basadas en las reglas definidas y mostrarlas en el área de texto de la interfaz gráfica. Además, invoca los métodos enviarInalcazables() y enviarInutiles() para calcular y mostrar los no terminales inalcanzables e inútiles. 
enviarInalcazables(): Este método calcula los no terminales inalcanzables utilizando el objeto lenguaje y los muestra en el campo de texto correspondiente en la interfaz gráfica. 
enviarInutiles(): Este método calcula los no terminales inútiles utilizando el objeto lenguaje y los muestra en el campo de texto correspondiente en la interfaz gráfica. 
actualizarGUI(): Este método actualiza los campos de texto en la interfaz gráfica con los valores actuales del objeto lenguaje. Se llama después de guardar los datos iniciales para reflejar los cambios en la interfaz gráfica. 
mostrarAlerta(String mensaje): Este método muestra una alerta en la interfaz gráfica con el mensaje especificado. Se utiliza para informar al usuario sobre errores o mensajes importantes. 
validarTxtInicial(String inicial): Este método verifica si el símbolo inicial ingresado por el usuario es válido. Verifica que solo haya un valor no terminal y que no contenga espacios en blanco. Se utiliza en el método Guardar() para validar el símbolo inicial antes de guardarlo en el objeto lenguaje. 




Instrucciones de instalación y ejecución:
Requisitos previos: 
Es necesario tener instalado Java Development Kit (JDK) en el sistema. Puede descargarlo e instalarlo desde la página oficial de Oracle.
Asi mismo si se requiere es fundamental tener instalado El JDK Zulu, el cual es una distribución del Kit de Desarrollo de Java (JDK) proporcionada por Azul Systems. Sirve para proporcionar una implementación de código abierto y de alta calidad de la Plataforma Java, incluido el compilador, las bibliotecas y las herramientas de desarrollo, que pueden ser utilizadas para desarrollar y ejecutar aplicaciones Java en diversos entornos. Esto lo puede realizar desde el sitio: https://www.azul.com/downloads/?package=jdk#zulu dependiendo de su sistema operativo el archivo .zip.
Instalación del proyecto: 
Clone o descarge el repositorio del proyecto desde GitHub:
bash
Copy code
it clone <https://github.com/MaferCorzo/AplicativoTC-1152299-1152300-.git>
Abra NetBeans IDE y cargue el proyecto clonado o descargado.
Ubique la clase AplicativoTC.java en el explorador de proyectos de NetBeans, la cual se encuentra en la siguiente ruta:
           css
           Copy cod
\AplicativoTC\src\tc
Ejecución del proyecto: 
De clic derecho en la clase AplicativoTC.java en el explorador de proyectos de NetBeans.
Seleccione "Run File" para ejecutar la aplicación.
La aplicación se iniciará y debería abrirse la interfaz de usuario.
Interacción con la aplicación: Utilice la interfaz de usuario proporcionada para interactuar con la aplicación. Dependiendo de la funcionalidad del programa, podrás cargar archivos, realizar operaciones, etc.
Consideraciones adicionales: Asegúrese de tener la versión adecuada de JavaFX y otras bibliotecas que puedan ser necesarias para la aplicación.
