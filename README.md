- 2 projects
  - logger is the light logger
  - springboot application is the client used for testing

Notes:
- included [postman collection](logger_and_app.postman_collection.json) for easier testing
- additional logger improvements in the [README.md](logger/README.md) file
- for simplicity, the spring app exposes a single controller with several options:
  - log -> need to provide the log level, in order to use the appropriate logger method
  - log/all -> logs on all levels
  - reconfigure -> provide the new logger configuration
- of course, the library can be used in some more simple apps, but configuration reloading must be implemented, as is currently does not support a "notification" mechanism