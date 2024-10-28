import os
import sys

def search_file_recursive(directory, filename, case_sensitive=True, found_files=None):
    """
    Recursively searches for a file within a directory and its subdirectories.
    :param directory: The root directory to start the search.
    :param filename: The name of the file to search for.
    :param case_sensitive: Boolean to indicate if the search should be case-sensitive.
    :param found_files: List to accumulate found file paths across recursive calls.
    :return: A list of full paths where the file is found.
    """
    if found_files is None:
        found_files = []

    try:
        for item in os.listdir(directory):
            item_path = os.path.join(directory, item)

            # If item is a directory, recurse into it
            if os.path.isdir(item_path):
                search_file_recursive(item_path, filename, case_sensitive, found_files)
            # If item is a file, check if it matches the filename
            elif os.path.isfile(item_path):
                if (case_sensitive and item == filename) or (not case_sensitive and item.lower() == filename.lower()):
                    found_files.append(item_path)
    except Exception as e:
        print(f"An error occurred while searching in '{directory}': {e}")

    return found_files

if __name__ == "__main__":
    # Check command-line arguments
    if len(sys.argv) < 3:
        print("Usage: python recursive_file_search.py <directory> <filename> [case_sensitive]")
        sys.exit(1)

    directory_path = sys.argv[1]
    file_name = sys.argv[2]
    case_sensitive = bool(sys.argv[3]) if len(sys.argv) > 3 else True

    # Check if directory exists
    if not os.path.isdir(directory_path):
        print(f"The specified directory does not exist: {directory_path}")
        sys.exit(1)

    # Search for the file
    print(f"Searching for '{file_name}' in directory '{directory_path}'...")
    results = search_file_recursive(directory_path, file_name, case_sensitive)

    # Display results
    if results:
        print(f"File found at the following locations:")
        for path in results:
            print(path)
    else:
        print("File not found.")
