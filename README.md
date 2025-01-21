# Simple Web Server Implementation

## Project Overview
The Simple Web Server is a Java-based implementation of an HTTP server. It is designed to serve static files (HTML, CSS, JavaScript, images, etc.) from a specified web root directory. 
The server is lightweight and can be extended to support additional functionalities, making it an ideal learning project for understanding HTTP protocols and server-client interactions.

## Features

### 1-File Serving
* Serves static files including HTML, CSS, JavaScript, and image files.
* Resolves MIME types dynamically for supported file types.
* Fallback mechanisms for unsupported file types (default to application/octet-stream).

### 2-Routing
* Supports user-friendly URLs without file extensions (e.g., /web instead of /web.html).
* Automatic resolution of default files (e.g., web.html) for directory paths.

### 3-Error Handling

* Logs detailed errors for missing files or inaccessible paths.
* Sends appropriate HTTP error codes for invalid requests (e.g., 404 Not Found).

### 4-Cross-Platform
* Compatible with any operating system that supports Java.

### 5-Logging
* Uses SLF4J for detailed server and client request logging.
* Debug logs for HTTP parsing and file resolution.

## Components

### 1. Main Server

* Class: ServerListenerThread

* Purpose: Manages incoming client connections and creates threads for handling requests.

* Key Methods:

  * `run()`: Listens for incoming connections and delegates requests to ConnectionHandlerThread.

### 2. Connection Handling

* Class: ConnectionHandlerThread

* Purpose: Handles individual client requests in separate threads to ensure concurrency.

* Key Features:

  * Parses HTTP requests using `HttpParser`.

  * Fetches requested files from the `WebRootHandler`.

  * Constructs and sends HTTP responses.

### 3. HTTP Parsing

* Class: HttpParser

* Purpose: Parses the HTTP request line and headers.

* Key Methods:

  * `parseRequest()`: Extracts HTTP method, URI, and version.

### 4. Web Root Management

* Class: WebRootHandler

* Purpose: Resolves file paths, validates existence, and reads file data.

* Key Methods:

  * `getFileMimeType(String relativePath)`: Determines the MIME type for a file.

  * `getFileByteArrayData(String relativePath)`: Reads file content as a byte array.

  * `getFileintoStringFormat(String relativePath)`: Reads file content as a string (for debugging or HTML serving).

## How It Works

### 1. Startup

* The server starts on a specified port and binds to all available network interfaces.

* The web root directory is validated to ensure it exists.

### 2. Request Handling

* When a client connects:

  * The request is parsed by HttpParser.

  * The relative path is resolved using WebRootHandler.

  * If the file exists, it is served with the appropriate MIME type.

  * If the file does not exist, a 404 Not Found response is returned.

### 3. Response Construction

* Responses are constructed with the following format:

  * Status Line: HTTP/1.1 200 OK or HTTP/1.1 404 Not Found.

  * Headers: Includes Content-Type and other relevant headers.

  * Body: Contains the file content or an error message.


## How to Run the Server

### Compile and Run

Compile the Java code:
```sh
javac -cp .;path/to/slf4j.jar;path/to/logback.jar *.java
```
Run the server:
```sh
java -cp .;path/to/slf4j.jar;path/to/logback.jar Core.ServerListenerThread <port> <web_root>
```
Access the Server

Open a browser and navigate to:
```sh
http://<server_ip>:<port>/<relative_path>
```
Example:
```sh
http://localhost:1050/housing
```
