# SpaceXLifts

## AppLifting Android exercise project
### Dependencies & libraries
- Kotlin (Coroutines and Flow)
- Jetpack Compose
- Koin
- Room DB & DataStore (for offline caching)
- Retrofit and Moshi for REST and parsing

### Architecture
- MVVM

### API
Doc: https://github.com/r-spacex/SpaceX-API/tree/master/docs#rspacex-api-docs

- Rocket `/rockets`
- Rocket launches `/launches/all`
- Company info `/company`

- Rockets are fetched just for showing the detail, baucause in `/launches/all` only IDs of rockets are returned.

### Data caching
- There are 2 major caching principles used - Room DB for sophisticated data queries and DataStore. Room is more future proof, the main benefit is not that easily seen now, but for future queries it's a good choice. 
  For smaller data like company info, settings, etc. - DataStore is used. Why DataStore for company info? There will be just one company stored, so make database entity is overkill for this kind of information.
- Another option for company info could be offline caching in Retrofit layer.   

### Current spend time: 7MH