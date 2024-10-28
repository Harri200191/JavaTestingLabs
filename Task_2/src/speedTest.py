import time
from main import generate_permutations, generate_unique_permutations, generate_permutations_iterative

def test_performance():
    test_cases = [
        ("abc", "All unique characters, 3 characters"),
        ("abcd", "All unique characters, 4 characters"),
        ("aabb", "Some duplicate characters, 4 characters"),
        ("abcde", "All unique characters, 5 characters"),
        ("aabbc", "Some duplicate characters, 5 characters"),
        ("aaaa", "All duplicate characters, 4 characters")
    ]

    results = []

    for test_string, description in test_cases:
        print(f"Testing with input: '{test_string}' ({description})")

        # Measure time for recursive permutations
        start_time = time.time()
        perms = generate_permutations(test_string)
        duration = time.time() - start_time
        results.append((description, "generate_permutations", len(perms), duration))
        print(f" - Recursive generate_permutations: {len(perms)} permutations in {duration:.6f} seconds")

        # Measure time for unique recursive permutations
        start_time = time.time()
        unique_perms = generate_unique_permutations(test_string)
        unique_duration = time.time() - start_time
        results.append((description, "generate_unique_permutations", len(unique_perms), unique_duration))
        print(f" - Unique Recursive generate_unique_permutations: {len(unique_perms)} unique permutations in {unique_duration:.6f} seconds")

        # Measure time for iterative permutations
        start_time = time.time()
        iterative_perms = generate_permutations_iterative(test_string)
        iterative_duration = time.time() - start_time
        results.append((description, "generate_permutations_iterative", len(iterative_perms), iterative_duration))
        print(f" - Iterative generate_permutations_iterative: {len(iterative_perms)} permutations in {iterative_duration:.6f} seconds\n")

    # Summary
    print("\nSummary of Performance Tests:")
    print("Description\t\t\t\t\tFunction\t\t\tPermutations\tTime (seconds)")
    for description, func, count, time_taken in results:
        print(f"{description:<40}{func:<45}{count:<15}{time_taken:.6f}")

if __name__ == "__main__":
    test_performance()
