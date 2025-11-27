# SmartAllies Incident Reporting System - Backend Implementation

## ✅ Completed Components

### 1. Project Structure
```
backend/
├── src/main/java/com/smartallies/incident/
│   ├── IncidentReportingApplication.java     # Main Spring Boot application
│   ├── controller/
│   │   └── ChatController.java               # REST API endpoints
│   ├── service/
│   │   ├── ChatOrchestrationService.java     # Main workflow orchestration
│   │   ├── LlmService.java                   # Ollama LLM integration
│   │   ├── ConversationContextService.java   # Session state management
│   │   └── ResourceService.java              # Static resource provider
│   ├── model/
│   │   ├── IncidentType.java                 # HUMAN, FACILITY, EMERGENCY
│   │   ├── WorkflowState.java                # Conversation states
│   │   ├── ConversationContext.java          # Session context model
│   │   └── IncidentClassification.java       # Classification result
│   ├── dto/
│   │   ├── ChatRequest.java                  # API request DTO
│   │   └── ChatResponse.java                 # API response DTO
│   ├── config/
│   │   ├── WebConfig.java                    # CORS configuration
│   │   └── EmergencyConfig.java              # Swiss emergency numbers
│   └── util/
│       └── PromptTemplates.java              # LLM prompt templates
├── src/main/resources/
│   └── application.properties                # Spring configuration
├── pom.xml                                   # Maven dependencies
├── docker-compose.yml                        # Ollama container setup
├── start.sh                                  # Automated startup script
└── README.md                                 # Documentation
```

### 2. Core Features Implemented

#### ✅ Incident Classification
- Automatic classification into HUMAN, FACILITY, or EMERGENCY
- Confidence scoring
- Classification confirmation workflow
- Reclassification on user disagreement

#### ✅ Three Distinct Workflows

**Human Incident:**
1. Empathetic tone and supportive messaging
2. Provides relevant HR resources and hotlines
3. Asks user if they want to draft a report
4. Collects mandatory fields: Who, What, When, Where
5. Generates professional summary
6. Offers Submit / Submit Anonymously / Cancel options

**Facility Incident:**
1. Collects What (description) and Where (location)
2. Mentions floor plan pinning capability
3. Optional picture recommendation
4. Generates summary for maintenance team

**Emergency Workflow:**
1. Immediate activation of emergency protocol
2. Displays all Swiss emergency numbers
3. Requests location urgently
4. Simulates Samaritan alert (logs warning)
5. Collects person name and condition
6. No submit buttons - real-time handling

#### ✅ LLM Integration
- Spring AI framework integration
- Ollama API connectivity
- Structured JSON prompt templates
- Robust response parsing with error handling
- Automatic JSON extraction from LLM responses

#### ✅ Session Management
- In-memory conversation context storage
- Session-based state tracking
- Field collection and validation
- Automatic context updates

#### ✅ API Endpoints
- `POST /api/chat` - Main conversation endpoint
- `GET /api/health` - Health check

### 3. Configuration

#### Ollama Settings
```properties
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.options.model=llama3.2
spring.ai.ollama.chat.options.temperature=0.7
spring.ai.ollama.embedding.options.model=nomic-embed-text
```

#### Emergency Numbers (Switzerland)
- Police: 117
- Ambulance: 144
- Fire: 118
- Samaritans: 143

#### CORS
- Configured for `http://localhost:5173` and `http://localhost:3000`
- Ready for React frontend integration

### 4. Prompt Engineering

All prompts are versioned and structured for:
- Consistent JSON output format
- Field extraction from user messages
- Context-aware responses
- Validation and error handling

### 5. Developer Experience

**Quick Start:**
```bash
cd backend
./start.sh
```

**Manual Start:**
```bash
ollama serve
ollama pull llama3.2
mvn spring-boot:run
```

**Testing:**
- Postman collection included (`postman_collection.json`)
- Unit test structure in place
- Health endpoint for monitoring

## 🚧 Not Yet Implemented (For Future)

### Database Integration
- Currently using in-memory storage
- Need PostgreSQL/MongoDB for persistence
- Report submission storage
- User management

### File Upload
- Image upload endpoint (`/api/upload`)
- Image storage and retrieval
- Image processing for LLM vision models

### ChromaDB Integration
- Vector database setup
- RAG for resource retrieval
- Document embeddings
- Similarity search for policies

### Authentication & Authorization
- User authentication
- Role-based access control
- Anonymous submission handling
- Session security

### Real Samaritan Alert System
- Integration with company alert system
- SMS/Email notifications
- Emergency contact management
- Alert escalation workflow

### Floor Plan Integration
- Floor plan image storage
- Pin placement coordinates
- Map rendering endpoint

### Monitoring & Logging
- Application monitoring
- LLM performance metrics
- Conversation analytics
- Error tracking

## 📊 Architecture Decisions

### Why In-Memory Storage?
- Fast development and testing
- No database setup required
- Easy to migrate to persistent storage later
- Suitable for MVP/prototype

### Why Ollama?
- Local deployment (data privacy)
- No API costs
- Fast response times
- Model flexibility

### Why Spring AI?
- Standardized LLM interface
- Easy to switch providers
- Built-in prompt management
- Active development

## 🎯 Testing the Backend

### Test Flow Examples

**Human Incident Flow:**
1. Send: "I'm experiencing harassment from my colleague"
2. Confirm: "Yes, that's correct"
3. Agree: "Yes, help me report this"
4. Provide: "It was John from accounting, yesterday at 2pm in the cafeteria"
5. Receive: Generated summary with submit options

**Emergency Flow:**
1. Send: "Someone collapsed in the office!"
2. System: Shows emergency numbers
3. Provide: "Third floor, near the elevators"
4. System: Alert sent to Samaritans
5. Continue: Provide person details

**Facility Flow:**
1. Send: "The door to conference room is broken"
2. Confirm: "Yes, correct"
3. Provide: "Main conference room on 2nd floor, door won't close"
4. Receive: Summary ready for facilities team

## 🔗 Integration with Frontend

The backend is ready to integrate with a React frontend:

**Example Frontend Call:**
```typescript
const response = await fetch('http://localhost:8080/api/chat', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    sessionId: generateSessionId(),
    message: userMessage,
    imageUrl: uploadedImageUrl || null
  })
});

const data = await response.json();
// data.message - Bot response
// data.incidentType - HUMAN | FACILITY | EMERGENCY
// data.workflowState - Current state
// data.suggestedActions - Button options
// data.resources - Links to show
// data.metadata - Additional info
```

## 📝 Code Quality

All code follows the maintainability guidelines in `.github/copilot-instructions.md`:
- ✅ Explicit types and interfaces
- ✅ Single responsibility functions
- ✅ Comprehensive error handling
- ✅ Structured logging
- ✅ JavaDoc documentation
- ✅ Clear naming conventions
- ✅ Versioned prompts

## 🚀 Next Steps

1. **Run the backend:**
   ```bash
   cd backend
   ./start.sh
   ```

2. **Test with Postman:**
   - Import `postman_collection.json`
   - Run the example requests

3. **Build the frontend:**
   - Create React PWA
   - Integrate with `/api/chat`
   - Implement UI for three workflows

4. **Add persistence:**
   - Set up database
   - Implement submission storage
   - Add user accounts

## 📧 Support

For issues or questions, check:
- `backend/README.md` - Detailed setup instructions
- `backend/postman_collection.json` - API examples
- Spring Boot logs - Detailed error messages
