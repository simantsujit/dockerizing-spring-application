
# Spring Boot + Reactive MongoDB Issue — Root Cause & Resolution

## 📌 Context
- Project: `job-service`
- Stack:
  - Java 17
  - Spring Boot (initially **4.0.1**, later **3.5.9**)
  - Spring WebFlux
  - Spring Data MongoDB Reactive
  - MongoDB (Docker)
  - Lombok (DTOs)

---

## ❌ Problems Faced

### 1. MongoDB Unauthorized Error
**Error**
```
Command execution failed on MongoDB server with error 13 (Unauthorized)
```

**Root Cause**
- MongoDB authentication configuration mismatch during early setup
- This was resolved later and was **not the final blocker**

---

### 2. DTO Serialization & Mapping Issues

#### a) Jackson Serialization Error
```
No serializer found for class JobDto
FAIL_ON_EMPTY_BEANS
```

#### b) API Response Returned `null` Fields
```json
{
  "id": null,
  "description": null,
  "company": null,
  "skills": null,
  "salary": null,
  "remote": null
}
```

**Root Cause**
- DTO created using no-args constructor
- Incorrect entity → DTO mapping
- Fields never populated

---

### 3. Lombok Constructor Not Found
**Compile Error**
```
constructor JobDto cannot be applied to given types
required: no arguments
found: String, String, String, Set<String>, Integer, Boolean
```

**Root Cause**
- Lombok annotation processing not effective due to framework incompatibility

---

## 🚨 CRITICAL ROOT CAUSE (Final)

### ❗ Spring Boot **4.0.1**
- Pre-release / experimental
- Incompatible with:
  - Lombok
  - Jackson defaults
  - Spring Data MongoDB Reactive
- Caused:
  - Missing Lombok-generated constructors
  - Serialization failures
  - Unstable WebFlux behavior

---

## ✅ Final Resolution

### ✔ Downgraded Spring Boot
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.5.9</version>
</parent>
```

### ✔ Result
- Lombok worked correctly
- DTO constructors generated
- Jackson serialization successful
- Reactive MongoDB queries worked
- `/job/all` API returned valid data

---

## ✅ Correct DTO

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private String id;
    private String description;
    private String company;
    private Set<String> skills;
    private Integer salary;
    private Boolean remote;
}
```

---

## ✅ Correct Reactive Mapping

```java
public Flux<JobDto> all() {
    return jobRepository.findAll()
        .map(job -> new JobDto(
            job.getId(),
            job.getDescription(),
            job.getCompany(),
            job.getSkills(),
            job.getSalary(),
            job.getRemote()
        ));
}
```

---

## 🧠 Key Learnings

1. ❌ Avoid Spring Boot 4.x for production
2. ✅ Prefer Spring Boot 3.5.x for stability
3. ⚠ Lombok issues can be framework-version related
4. ✅ Always verify constructors at compile time
5. ⭐ Prefer Java `record` for DTOs in Spring Boot 3+

---

## 🏁 Final Conclusion

The issue was **not MongoDB**, **not DTO code**, and **not WebFlux**.
The real root cause was using **Spring Boot 4.0.1**.
Downgrading to **Spring Boot 3.5.9** resolved all issues.
