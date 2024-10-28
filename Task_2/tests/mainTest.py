import unittest
from ..src.main import generate_permutations, generate_unique_permutations

class TestStringPermutations(unittest.TestCase):
    
    def test_permutations_of_empty_string(self):
        """Test the permutations of an empty string."""
        result = generate_permutations("")
        self.assertEqual(result, [""])

    def test_permutations_of_single_character(self):
        """Test the permutations of a single-character string."""
        result = generate_permutations("a")
        self.assertEqual(result, ["a"])

    def test_permutations_of_two_characters(self):
        """Test the permutations of a two-character string."""
        result = generate_permutations("ab")
        expected = ["ab", "ba"]
        self.assertEqual(sorted(result), sorted(expected))

    def test_permutations_of_three_characters(self):
        """Test the permutations of a three-character string."""
        result = generate_permutations("abc")
        expected = ["abc", "acb", "bac", "bca", "cab", "cba"]
        self.assertEqual(sorted(result), sorted(expected))

    def test_permutations_with_duplicates(self):
        """Test the permutations with duplicate characters."""
        result = generate_permutations("aab")
        expected = ['aab', 'aab', 'aba', 'aba', 'baa', 'baa']
        self.assertEqual(sorted(result), sorted(expected))

    def test_unique_permutations_with_duplicates(self):
        """Test the unique permutations with duplicate characters."""
        result = generate_unique_permutations("aab")
        expected = ["aab", "aba", "baa"]
        self.assertEqual(sorted(result), sorted(expected))

    def test_large_string(self):
        """Test performance on a larger string with unique characters."""
        result = generate_permutations("abcd")
        # There should be 4! = 24 permutations for a 4-character unique string
        self.assertEqual(len(result), 24)
    
    def test_unique_permutations_with_all_duplicates(self):
        """Test the unique permutations with all characters identical."""
        result = generate_unique_permutations("aaa")
        # Since all characters are the same, only one unique permutation is expected
        self.assertEqual(result, ["aaa"])

if __name__ == "__main__":
    unittest.main()
