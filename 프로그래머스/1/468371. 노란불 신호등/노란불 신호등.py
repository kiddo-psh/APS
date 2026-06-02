from math import gcd


def solution(signals):
    
    periods = []
    lcm = 1
    
    for green, yellow, red in signals:
        cycle = green + yellow + red
        yellow_start = green + 1
        yellow_end = green + yellow
        
        periods.append((cycle, yellow_start, yellow_end))
        lcm = lcm * cycle // gcd(lcm, cycle)
        
    
    for t in range(lcm+1):
        if all(start <= t % cycle <= end
              for cycle, start, end in periods):
            return t
    
    return -1