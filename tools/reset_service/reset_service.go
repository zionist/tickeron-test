package main

import(
    "fmt"
    "os/exec"
    "os"
    "net/http"
    "path/filepath"
)


func stop(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintf(w, "Service stop started\n")
    dir, err := filepath.Abs(filepath.Dir(os.Args[0]))
    if err != nil {
       fmt.Println("Error: ", err)
    }
    c := exec.Command(fmt.Sprintf("%s\\%s", dir, "_pool_stop.bat"))
    fmt.Println(fmt.Sprintf("%s\\%s", dir, "_pool_stop.bat"))
    if err := c.Run(); err != nil {
        fmt.Fprintf(w, fmt.Sprintf("Error: %s\n", err))
    }
    fmt.Fprintf(w, "Service stop finished\n")
}

func start(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintf(w, "Service start started\n")
    dir, err := filepath.Abs(filepath.Dir(os.Args[0]))
    if err != nil {
       fmt.Println("Error: ", err)
    }
    c := exec.Command(fmt.Sprintf("%s\\%s", dir, "_pool_start.bat"))
    fmt.Println(fmt.Sprintf("%s\\%s", dir, "_pool_start.bat"))
    if err := c.Run(); err != nil {
        fmt.Fprintf(w, fmt.Sprintf("Error: %s\n", err))
    }
    fmt.Fprintf(w, "Service start finished\n")
}

func db_reset(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintf(w, "Service db_reset started\n")
    dir, err := filepath.Abs(filepath.Dir(os.Args[0]))
    if err != nil {
       fmt.Println("Error: ", err)
    }
    c := exec.Command(fmt.Sprintf("%s\\%s", dir, "_db_reset.bat"))
    fmt.Println(fmt.Sprintf("%s\\%s", dir, "_db_reset.bat"))
    if err := c.Run(); err != nil {
        fmt.Fprintf(w, fmt.Sprintf("Error: %s\n", err))
    }
    fmt.Fprintf(w, "Service db_rest finished\n")
}


func main() {
    http.HandleFunc("/stop", stop)
    http.HandleFunc("/db_reset", db_reset)
    http.HandleFunc("/start", start)
    http.ListenAndServe(":8089", nil)
}
