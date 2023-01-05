package tool

import (
	"fmt"
	"os"
)

func GenerateFile(path string, info os.FileInfo, err error) error {
	if err != nil {
		fmt.Println(err)
		return err
	}
	fmt.Printf("dir: %v: name: %s\n", info.IsDir(), path)
	return nil
}
