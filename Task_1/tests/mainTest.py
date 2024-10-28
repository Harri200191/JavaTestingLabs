import unittest
import tempfile
import os
from src.main import search_file_recursive

class TestRecursiveFileSearch(unittest.TestCase):

    def setUp(self):
        """Create a temporary directory structure for testing."""
        self.test_dir = tempfile.TemporaryDirectory()

        # Create directories and files for testing
        os.makedirs(os.path.join(self.test_dir.name, "dir1"))
        os.makedirs(os.path.join(self.test_dir.name, "dir2"))
        os.makedirs(os.path.join(self.test_dir.name, "dir1", "subdir1"))

        with open(os.path.join(self.test_dir.name, "file1.txt"), 'w') as f:
            f.write("Test file 1")
        with open(os.path.join(self.test_dir.name, "dir1", "file2.txt"), 'w') as f:
            f.write("Test file 2")
        with open(os.path.join(self.test_dir.name, "dir1", "subdir1", "file3.txt"), 'w') as f:
            f.write("Test file 3")
        with open(os.path.join(self.test_dir.name, "dir2", "File4.txt"), 'w') as f:
            f.write("Test file 4")

    def tearDown(self):
        """Cleanup the temporary directory after tests."""
        self.test_dir.cleanup()

    def test_file_found_in_root(self):
        """Test searching for a file located in the root directory."""
        result = search_file_recursive(self.test_dir.name, "file1.txt")
        self.assertEqual(len(result), 1)
        self.assertTrue(result[0].endswith(os.path.join("file1.txt")))

    def test_file_found_in_subdirectory(self):
        """Test searching for a file located in a subdirectory."""
        result = search_file_recursive(self.test_dir.name, "file2.txt")
        self.assertEqual(len(result), 1)
        self.assertTrue(result[0].endswith(os.path.join("dir1", "file2.txt")))

    def test_file_found_in_nested_subdirectory(self):
        """Test searching for a file located in a nested subdirectory."""
        result = search_file_recursive(self.test_dir.name, "file3.txt")
        self.assertEqual(len(result), 1)
        self.assertTrue(result[0].endswith(os.path.join("dir1", "subdir1", "file3.txt")))

    def test_case_insensitive_search(self):
        """Test case-insensitive search."""
        result = search_file_recursive(self.test_dir.name, "file4.txt", case_sensitive=False)
        self.assertEqual(len(result), 1)
        self.assertTrue(result[0].endswith(os.path.join("dir2", "File4.txt")))

    def test_file_not_found(self):
        """Test searching for a file that does not exist."""
        result = search_file_recursive(self.test_dir.name, "non_existent_file.txt")
        self.assertEqual(len(result), 0)

    def test_multiple_occurrences(self):
        """Test searching for multiple files with the same name."""
        # Create an additional file with the same name
        with open(os.path.join(self.test_dir.name, "dir1", "subdir1", "file1.txt"), 'w') as f:
            f.write("Duplicate file 1")
        result = search_file_recursive(self.test_dir.name, "file1.txt")
        self.assertEqual(len(result), 2)
        paths = [path for path in result if path.endswith(os.path.join("file1.txt"))]
        self.assertEqual(len(paths), 2)

if __name__ == "__main__":
    unittest.main()
