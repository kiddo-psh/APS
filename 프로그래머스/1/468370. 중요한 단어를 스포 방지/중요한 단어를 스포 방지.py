def solution(message, spoiler_ranges):
    
    spoiler_words = set()
    normal_words = set()
    
    idx = 0
    
    for word in message.split(" "):
        start = idx
        end = start + len(word) - 1
        idx += len(word) + 1
        
        is_spoiler = any(
            start <= right and end >= left
            for left, right in spoiler_ranges
        )
                
        if is_spoiler:
            spoiler_words.add(word)
        else:
            normal_words.add(word)
    
    return len(spoiler_words - normal_words)