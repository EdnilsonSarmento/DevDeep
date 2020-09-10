#[Developer Challenge] Ednilson Sarmento

##Rest Client

###Conceito
* Desenvolver um cliente REST que permita obter e visualizar as informações de propriedades dos países presentes na API (https://restcountries.eu) como nome, capital, região, sub-região, população, área, fuso horário, nome nativo e o link para visualizar a bandeira.

* Implementar um mecanismo dentro do cliente REST para exportar as informações dos países para o formato XLS, CSV e XML.

###Tecnologias por min Usadas

* Intelij IDEA

* Spring Boot with Maven

* Java Code

* HTML, CSS, BootStrap

###Estrutura e Funcionalidade
A aplicação conta com simples Pagina Html (index) cuja função É 
exibir as funcionalidades ao usuario.

As Funcionalidades foram dadas Vidas baseando-se na API Rest 
https://restcountries.eu juntamente com algumas artemanhas 
do codigo java.

A aplicação contem a class **RestController.java** onde 90% do 
codigo BackEnd acontece. Nela Apartir do metodo **getCountries()**
pode-se obter a partir da URL da API Rest as Propriedades dos Diferentes 
Paises de maneira como desejamos.

Apos Obtermos As Propriedades dos Paises, Gerou-se os ficheiros
XML,CSV e XLS( **arquivosExportados.fromJSON.csv,
 arquivosExportados.fromJSON.xml**) de maneira Pretendida.

Pude acrescentar a funcionalidade de Exportação, que permite
que o Usuario tenha acesso ao arquivo no formato desejado em sua
maquina.

Portanto, de modo geral, assim que aplicação inicia os arquivos
nção existem, assim que o usuario vai navegando entre as funcionalidades
os arquivos são gerados.

**N.B:** O uso de **Internet** e' imperioso, esta para poder obter
os recursos da Rest API, e para melhorar a visualização da 
pagina inicial(index) que tambem foi estilizada usando recursos
da **Internet**.


Grato pela Atenção!!
