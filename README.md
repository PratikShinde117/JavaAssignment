

The API processes a mixed array of inputs and categorizes data into numbers, alphabets, and special characters while providing additional analytics such as frequency counts, duplicate detection, sorting, and summary statistics.

---

## Features

### Input Processing

- Numbers
- Negative Numbers
- Decimal Numbers
- Alphabets
- Special Characters
- Alphanumeric Strings

### Data Analysis

- Odd Numbers
- Even Numbers
- Sum of Numbers
- Largest Number
- Smallest Number
- Sorted Numbers
- Alphabet Frequency
- Vowel Count
- Duplicate Detection
- Unique Element Count
- Processing Time Calculation

### Request Handling

- Accepts `X-Request-Id` header
- Returns the same Request ID in response
- Ignores:
  - Null values
  - Empty strings
  - Whitespace-only strings
- Removes duplicate values before processing

### Architecture

- Controller Layer
- Service Layer
- DTO Layer
- Global Exception Handling
- Bean Validation

---

## Technology Stack

- Java 21
- Spring Boot 3
- Maven
- Lombok
- JUnit 5
- Mockito

---

## Project Structure

```text
src/main/java/com/pratik/bfhl

├── controller
│   └── BFHLController.java

├── dto
│   ├── RequestDTO.java
│   ├── ResponseDTO.java
│   └── SummaryDTO.java

├── service
│   ├── BFHLService.java
│   └── BFHLServiceImpl.java

├── exception
│   └── GlobalExceptionHandler.java

└── BfhlApplication.java
```

---

## API Endpoint

### POST /bfhl

### Request Header

```http
X-Request-Id: REQ-1001
```

### Request Body

```json
{
  "data": [
    "ABC",
    "123",
    "A1B2",
    "$",
    "%",
    "-50",
    "0",
    "xyz",
    "",
    null,
    "999",
    "Test99",
    "&"
  ]
}
```

---

## Sample Response

```json
{
  "is_success": true,
  "requestId": "REQ-1001",
  "oddNumbers": [
    "123",
    "999"
  ],
  "evenNumbers": [
    "-50",
    "0"
  ],
  "alphabets": [
    "ABC",
    "A",
    "B",
    "XYZ",
    "T",
    "E",
    "S",
    "T"
  ],
  "specialCharacters": [
    "$",
    "%",
    "&"
  ],
  "sum": "1174",
  "largestNumber": "999",
  "smallestNumber": "-50",
  "alphabetCount": 12,
  "numberCount": 7,
  "specialCharacterCount": 3,
  "containsDuplicates": false,
  "vowelCount": 3,
  "alphabetFrequency": {
    "A": 2,
    "B": 2,
    "C": 1,
    "S": 1,
    "T": 2,
    "E": 1,
    "X": 1,
    "Y": 1,
    "Z": 1
  },
  "uniqueElementCount": 11,
  "sortedNumbers": [
    "-50",
    "0",
    "1",
    "2",
    "99",
    "123",
    "999"
  ]
}
```

---

## Running Locally

### Clone Repository

```bash
git clone https://github.com/<your-username>/bfhl.git
```

### Navigate to Project

```bash
cd bfhl
```

### Run Application

```bash
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

---

## Testing

Example Request:

```http
POST http://localhost:8080/bfhl
```

Header:

```http
X-Request-Id: REQ-1001
```

Body:

```json
{
  "data": [
    "A",
    "1",
    "22",
    "$",
    "B",
    "7"
  ]
}
```

---

## Future Improvements

- Swagger/OpenAPI Documentation
- Integration Testing
- Asynchronous Processing
- Correlation ID Support
- Docker Containerization
- Cloud Deployment

---

## Author

Pratik Shinde

B.E. Computer Engineering  
Dr. D. Y. Patil Institute of Technology, Pimpri, Pune
