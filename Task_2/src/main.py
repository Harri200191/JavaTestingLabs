from itertools import permutations

def generate_permutations(s):
    """
    Recursively generates all permutations of a given string.
    :param s: The input string to generate permutations for.
    :return: A list of all permutations of the string.
    """
    # Handle base cases
    if len(s) <= 1:
        return [s]

    # List to store all permutations
    permutations = []

    # Loop through each character and recursively generate permutations
    for i, char in enumerate(s):
        # Exclude the character at the current position and find permutations of the rest
        remaining_chars = s[:i] + s[i+1:]

        # Generate all permutations of the remaining characters
        for perm in generate_permutations(remaining_chars):
            permutations.append(char + perm)

    return permutations

def generate_unique_permutations(s):
    """
    Recursively generates all unique permutations of a given string.
    :param s: The input string to generate permutations for.
    :return: A list of all permutations of the string.
    """
    if len(s) <= 1:
        return [s]
    permutations = set()
    for i, char in enumerate(s):
        remaining_chars = s[:i] + s[i+1:]
        for perm in generate_unique_permutations(remaining_chars):
            permutations.add(char + perm)
    return list(permutations)

def generate_permutations_iterative(s):
    """
    Iteratively generates all permutations of a given string using itertools.
    :param s: The input string to generate permutations for.
    :return: A list of all permutations of the string.
    """
    return [''.join(p) for p in permutations(s)]

# Example Usage
if __name__ == "__main__":
    input_string = input("Enter a string to generate its permutations: ")
    if input_string:
        all_permutations = generate_permutations(input_string)
        print(f"Permutations of '{input_string}':")
        print(all_permutations)
    else:
        print("Input string cannot be empty.")
