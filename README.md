# SpaceXLifts

## AppLifting Android exercise project
### Dependencies & libraries
- Kotlin (Coroutines and Flow)
- Jetpack Compose
- Koin
- Room DB & DataStore (for offline caching)
- Retrofit and Moshi for REST and parsing
- Jetpack Navigation

### Architecture
- MVVM

### API
[Api doc](https://github.com/r-spacex/SpaceX-API/tree/master/docs#rspacex-api-docs)

Used endpoints:
- Rocket `/rockets`
- Rocket launches `/launches`
- Company info `/company`

- Rockets are fetched to get rocket's info, because in `/launches` only IDs of rockets are returned

### Data caching
- There are 2 major caching principles used - Room DB for data queries (filtering, ...) and DataStore as a simple store - like company info, settings, etc.
- Another option for company info could be offline caching in Retrofit layer (like LRU cache) set max-age for a few days (perhaps not changing too frequently)

### Testing
- Something that is quite new for me, so I would definitely like to improve and focus to this area.

### Link
- [Compose Test cheatsheet](https://developer.android.com/jetpack/compose/testing-cheatsheet)


### Current spend time: 11MH